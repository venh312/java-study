## ITEM 87 - 커스텀 직렬화 형태를 고려해보라.
클래스가 `Serializable` 을 구현하고 기본 직렬화 형태를 사용한다면 현재의 구현에 종속적이게 된다. 
즉, 기본 직렬화 형태를 버릴 수 없게 된다. 따라서 유연성, 성능, 정확성과 같은 측면을 고민한 후에 합당하다고 생각되면 기본 직렬화 형태를 사용해야 한다.

### 이상적인 직렬화 형태
기본 직렬화 형태는 객체가 포함한 데이터뿐만 아니라 그 객체를 시작으로 접근할 수 있는 모든 객체와 객체들의 연결된 정보까지 나타낸다. 
이상적인 직렬화 형태라면 물리적인 모습과 독립된 논리적인 모습만을 표현해야 한다. 
객체의 물리적 표현과 논리적 내용이 같다면 기본 직렬화 형태를 선택해도 무방하다. 예를 들어 사람의 이름을 표현하는 클래스는 괜찮을 것이다.

```java
public class Name implements Serializable {
    /**
    * 성. null이 아니어야 한다.
    * @serial
    */
    private final Stirng lastName;

    /**
     * 이름. null이 아니어야 한다.
     * @serial
     */
    private final String firstName;

    /**
     * 중간이름. 중간이름이 없다면 null
     * @serial
     */
    private final String middleName;
}
```

### readObject - 직렬화된 객체를 역직렬화할 때 호출되는 메서드
기본 직렬화 형태가 적합해도 불변식 보장과 보안을 위해 `readObject` 메서드를 제공해야 하는 경우가 많다.
앞에서 살펴본 Name 클래스를 예로 들면, lastName과 firstName 필드가 null이 아님을 `readObject` 메서드가 보장해야 한다.

### transient
- `transient` 키워드가 붙은 필드는 기본 직렬화 형태에 포함되지 않는다. 
- 기본 직렬화를 사용한다면 역직렬화할 때 `transient` 필드는 기본값으로 초기화된다.
- 클래스의 모든 필드가 `transient`로 선언되었더라도 `writeObject`와 `readObject` 메서드는 각각 `defaultWriteObject`와 `defaultReadObject` 메서드를 호출한다. 
- 직렬화 명세에는 이 과정을 무조건 할 것을 요구한다. 이렇게 해야 향후 릴리즈에서 `transient`가 아닌 필드가 추가되더라도 상위와 하위 모두 호환이 가능하기 때문이다

```java
private transient int size = 0;
```

### 동기화
기본 직렬화 사용 여부와 상관없이 직렬화에도 동기화 규칙을 적용해야 한다. 
예를 들어 모든 메서드를 `synchronized`로 선언하여 스레드 안전하게 만든 객체에 기본 직렬화를 사용한다면, `writeObject`도 아래처럼 수정해야 한다.
```java
private synchronized void writeObject(ObjectOutputStream stream) throws IOException {
    stream.defaultWriteObject();
}
```

### SerialVersionUID
- 어떤 직렬화 형태를 선택하더라도 직렬화가 가능한 클래스에는 SerialVersionUID(이하 SUID)를 명시적으로 선언해야 한다. 
- 선언하지 않으면 자동 생성되지만 런타임에 이 값을 생성하느라 복잡한 연산을 수행해야 한다.

### 무작위로 고른 long 값
```java
private static final long serialVersionUID = 0204L;
```
SUID가 꼭 유니크할 필요는 없다. 다만 이 값이 변경되면 기존 버전 클래스와의 호환을 끊게 되는 것이다. 따라서 호환성을 끊는 경우가 아니라면 SUID 값을 변경해서는 안 된다.