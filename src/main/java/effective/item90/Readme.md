## ITEM 90 - 직렬화된 인스턴스 대신 직렬화 프록시 사용을 검토하라.
### Serializable을 implements한 순간
정상적인 인스턴스 생성 방법인 생성자 이외의 방법이 생기게 된다. 버그와 보안 문제가 생길 가능성이 커진다는 것이다. 하지만 직렬화 프록시 패턴을 사용하면 위험을 크게 줄일 수 있다.

### 직렬화 프록시 패턴
바깥 클래스의 논리적 상태를 표현하는 중첩 클래스를 설계하여 `private static`으로 선언하다. 여기서 중첩 클래스가 직렬화 프록시다.   
중첩 클래스의 생성자는 단 하나여야 하며, 바깥 클래스를 매개변수로 받아야 한다. 단순히 인수로 넘어온 인스턴스의 데이터를 복사한다. 일관성 검사 또는 방어적 복사도 필요가 없다. 
다만 바깥 클래스와 직렬화 프록시 모두 `Serializable`을 구현해야 한다.

```java
class Period implements Serializable {
    private final Date start;
    private final Date end;

    public Period(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 2123123123;
        private final Date start;
        private final Date end;

        public SerializationProxy(Period p) {
            this.start = p.start;
            this.end = p.end;
        }

        /**
         * Deserialize 할 때 호출된다.
         * 오브젝트를 생성한다.
         */
        private Object readResolve() {
            return new Period(start, end);
        }
    }

    /**
     * 이로 인해 바깥 클래스의 직렬화된 인스턴스를 생성할 수 없다.
     * 직렬화할 때 호출되는데, 프록시를 반환하게 하고 있다.
     *
     * Serialize할 때 호출된다.
     */
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    /**
     * readObject, writeObject 가 있다면, 기본적으로 Serialization 과정에서
     * ObjectInputStream, ObjectOutputStream이 호출하게 된다.
     * 그 안에 커스텀 로직을 넣어도 된다는 것.
     */
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        // readObject는 deserialize할 때, 그러니까 오브젝트를 만들 때인데.
        // 이렇게 해두면, 직접 Period로 역직렬화를 할 수 없는 것이다.
        throw new InvalidObjectException("프록시가 필요해요.");
    }
}
```

### 직렬화 프록시 패턴의 장점
- 앞선 예제 코드에서 본 것처럼 멤버 필드를 `final`로 선언할 수 있기 때문에 진정한 불변으로 만들 수 있다. 
- 직렬화 프록시 패턴은 역직렬화한 인스턴스와 원래의 직렬화된 클래스가 달라도 정상적으로 동작한다.

### EnumSet
대표적인 예로 `EnumSet`은 public 생성자 없이 정적 팩터리만 제공한다. 원소 개수가 64개 이하면 `RegularEnumSet`을 사용하고 그보다 크면 `JumboEnumSet`을 사용한다.
그런데, 64개짜리 원소를 가진 `EnumSet`을 직렬화한 다음에 원소 5개를 추가하고 역직렬화하면 어떻게 될까? 간단히 역직렬화할 때 `JumboEnumSet`으로 하면 된다. 
이게 가능한 이유는 `EnumSet`에는 직렬화 프록시 패턴이 적용되어 있기 때문이다.

### 직렬화 프록시 패턴의 한계
- 클라이언트가 마음대로 확장할 수 있는 클래스에는 적용할 수 없다. 
- 또한 객체가 서로 참조하는 상황, 그러니까 객체 그래프에 순환이 있는 클래스에도 적용할 수 없다. 
- 이런 객체의 메서드를 직렬화 프록시의 `readResolve` 안에서 호출하려 하는 경우 예외가 발생할 것이다. 
  - 직렬화 프록시만 가진 것이지 실제 객체는 아직 만들어지지 않았기 때문이다. 또한 방어적 복사보다 상대적으로 속도도 느리다.
