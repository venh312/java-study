## ITEM 33 - 타입 안전 이종 컨테이너를 고려하라.

### 이종 안전 컨테이너 패턴 (type safe heterogeneous container pattern)
- 컨테이너에 값을 넣거나 뺄 때 매개변수화한 키를 함께 제공한다.
- 이렇게 하면 제네릭 타입 시스템이 값의 타입이 키와 같음을 보장해줄 것이다.

### 코드 33-1
- 이 방식이 동작하는 이유는 class 의 클래스가 제네릭이기 때문이다.
- class 의 리터럴 타입은 Class 가 아닌 Class<T> 이다.
- String.class 타입은 Class<String>, Integer.class 타입은 Class<Integer>

### Favorites 는 타입 안전 이종 컨테이너라 할 만하다.
Favorites 인스턴스는 타입 안전한다. String 을 요청했는데 Integer 를 반환하는 일은 절대 없다. 일반적인 Map 과 달리 여러가지 타입의 원소를 담을 수 있다.

### Favorite 클래스에서 타입 안전을 보장하는 비결은 cast 메서드에 있다.
cast 메서드의 반환 타입은 Class 객체의 타입 매개변수와 같다. 즉, cast 메서드는 Class 클래스가 제네릭이라는 이점을 잘 활용한다.

```java
public class Class<T> {
    T cast(Object object);
}
```

이 기능은 getFavorite 메서드에 필요한 기능으로 T로 비검사 형변환하는 과정 없이도 Favorites 를 타입 안전하게 만들어준다.

### 정리
- 컬렉션 API로 대표되는 일반적인 제네릭형태는 한 컨테이너가 다룰수 있는 매개변수의 수가 고정적이다.
- 하지만 컨테이너자체가 아닌 키를 타입 매개변수로 바꾸면 이런 제약이 없는 타입 안전 이종 컨테이너를 만들 수 있다.
- 타입 이종 컨테이너는 Class를 키로 사용하며, 이런 식으로 쓰이는 Class 객체를 타입 토큰이라 한다.
- 또한 직접 구현한 키 타입도 쓸 수 있다. 예컨데 DB의 행(컨테이너)을 표현한 DatabaseRow 타입에는 제네릭 타입인 Column<T>를 사용할 수 있다.
- 하지만 타입이종 컨테이너를 사용하는데 제약이 있으니 이런 제약들을 주의해서 사용하자.
