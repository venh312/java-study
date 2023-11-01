## ITEM 88 - readObject 메서드는 방어적으로 작성하라.

### 방어적 복사를 사용하는 불변 클래스
```java
public final class Period {
    private final Date start;
    private final Date end;
    /**
     * @param  start 시작 시각
     * @param  end 종료 시각; 시작 시각보다 뒤여야 한다.
     * @throws IllegalArgumentException 시작 시각이 종료 시각보다 늦을 때 발생한다.
     * @throws NullPointerException start나 end가 null이면 발생한다.
     */
    public Period(Date start, Date end) {
        this.start = new Date(start.getTime()); // 가변인 Date 클래스의 위험을 막기 위해 새로운 객체로 방어적 복사
        this.end = new Date(end.getTime());

        if (this.start.compareTo(this.end) > 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }
    }

    public Date start() { return new Date(start.getTime()); }
    public Date end() { return new Date(end.getTime()); }
    public String toString() { return start + " - " + end; }
    // ... 나머지 코드는 생략
}
```
물리적 표현과 논리적 표현이 같기 때문에 기본 직렬화 형태를 사용해도 무방하다. 
따라서 `Serializable`만 구현하면 될 것 같다. 하지만 실제로는 불변식을 보장하지 못하게 된다. `readObject`가 또 다른 `public 생성자`이기 때문이다.

`readObject` 메서드도 생성자와 같은 수준으로 주의해야 한다. 인수가 유효한지 검사하고, 매개변수를 방어적으로 복사해야 한다. 그렇지 않다면 불변식을 깨뜨리는 공격으로부터 취약해질 수 있다.


### readObject 메서드
- `readObject` 메서드는 매개변수로 바이트 스트림을 받는 생성자라고 할 수 있다. 
- 보통 바이트 스트림은 정상적으로 생성된 인스턴스를 직렬화해서 만들어진다. 
- 하지만 불변을 깨뜨릴 의도로 만들어진 바이트 스트림을 받으면 문제가 생긴다. 
- 정상적인 방법으로는 만들어낼 수 없는 객체를 생성할 수 있기 때문이다.

단순하게 앞서 살펴본 Period 클래스에 `Serializable` 구현을 추가했다고 가정했을 때, 아래와 같은 코드는 불변식을 깨뜨리는 공격을 할 수 있다.

```java
public class BogusPeriod {
    // 진짜 Period 인스턴스에서는 만들어질 수 없는 바이트 스트림,
    // 정상적인 Period 인스턴스를 직렬화한 후에 손수 수정한 바이트 스트림이다.
    private static final byte[] serializedForm = {
        (byte)0xac, (byte)0xed, 0x00, 0x05, 0x73, 0x72, 0x00, 0x06,
        0x50, 0x65, 0x72, 0x69, 0x6f, 0x64, 0x40, 0x7e, (byte)0xf8,
        ... 생략
    }

    // 상위 비트가 1인 바이트 값들은 byte로 형변환 했는데,
    // 이유는 자바가 바이트 리터럴을 지원하지 않고 byte 타입은 부호가 있는(signed) 타입이기 때문이다.

    public static void main(String[] args) {
        Period p = (Period) deserialize(serializedForm);
        System.out.println(p);
    }

    // 주어진 직렬화 형태(바이트 스트림)로부터 객체를 만들어 반환한다.
    static Object deserialize(byte[] sf) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(sf)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
                return objectInputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
```

### 어떻게 방어할 수 있을까?
#### 1.`readObject` 메서드가 `defaultReadObject`를 호출하게 한 후에 역직렬화된 객체가 유효한지 검사해야 한다. 여기서 유효성 검사에 실패한다면 `InvalidObjectException`을 던져 잘못된 역직렬화가 발생하는 것을 막아야 한다. 

```java
private void readObject(ObjectInputStream s)
throws IOException, ClassNotFoundException {

    // 불변식을 만족하는지 검사한다.
    if (start.compareTo(end) > 0) {
        throw new InvalidObjectException(start + "after" + end);
    }
}
```

#### 2. 역직렬화를 할 때는 클라이언트가 접근해서는 안 되는 객체 참조를 갖는 필드는 모두 방어적으로 복사를 해야 한다.
```java
private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
    s.defaultReadObject();

    // 가변 요소들을 방어적으로 복사한다.
    start = new Date(start.getTime());
    end = new Date(end.getTime());

    // 불변식을 만족하는지 검사한다.
    if (start.compareto(end) > 0) {
        throw new InvalidObjectException(start + " after " + end);
    }
}
```

### 정리
- `readObject` 메서드를 작성할 때는 언제나 `public 생성자`를 작성하는 자세로 임해야 한다.
- `readObject` 메서드는 어떤 바이트 스트림이 넘어오더라도 유효한 인스턴스를 만들어내야 한다.
  - 이 바이트 스트림이 항상 진짜 직렬화된 인스턴스라고 믿으면 안 된다.
- 안전한 `readObject` 메서드를 작성하는 지침
  - private 이여야 하는 객체 참조 필드는 각 필드가 가리키는 객체를 방어적으로 복사하라.
  - 모든 불변식을 검사하고, 어긋난다면 `InvalidObjectException`을 던져라.
  - 역직렬화 후 객체 그래프 전체의 유효성을 검사해야 한다면 `ObjectInputValidation`를 사용하라.
  - `재정의(Overriding)` 가능한 메서드는 호출하지 말자.