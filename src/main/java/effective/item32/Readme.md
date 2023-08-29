## ITEM 32 - 제네릭과 가변인수를 함께 쓸 때는 신중하라.
가변인수(varargs) 메서드(아이템53)와 제네릭은 자바 5 에서 함께 추가되었으니 서로 잘 어우러지리라 기대하겠지만, 그렇지 않다.

### 가변인수 메서드의 허점
- 가변인수 메서드를 호출하면 가변인수를 담기 위한 배열이 자동으로 하나 만들어진다.
- 내부로 감춰야 했을 배열이 클라이언트에 노출하는 문제가 생겼다.
- varargs 매개변수에 제네릭이나 매개변수화 타입이 포함되면 컴파일 경고가 발생한다.

### 제네릭 배열을 프로그래머가 직접 생성하는 건 허용하지 않으면서 제네릭 varargs 매개변수를 받는 메서드를 선언할 수 있게 한 이유는 무엇일까?
코드 28-3은 오류를 내면서 코드 32-1은 경고로 끝내는 이유는 무엇일까?
- 제네릭이나 매개변수화 타입의 varargs 매개변수를 받는 메서드가 실무에서 매우 유용하기 때문이다.
- 그래서 언어 설계자는 이 모순을 수용하기로 했고, 실제 자바 라이브러리도 이런 메서드를 여럿 제공한다.

`Arrays.asList(T...a)`, `Collections.addAll(Collection<? super T>) c, T.. elements)`, `EnumSet.of(E first, E.. rest)`

### 자바 7 전에는 제네릭 가변인수 메서드의 작성자가 호출자 쪽에서 발생하는 경고에 대해서 해줄수 있는 일이 없었다.
따라서 이런 메서드는 사용하기 꺼림칙했고, 아래 애너테이션을 달아 경고를 숨겼다.
- @SuppressWarnings("unchecked")

### 자바 7 에서는 `@SafeVarargs` 어너테이션이 추가되어 제네릭 가변인수 메서드 작성자가 클라이언트 측에서 발생하는 경고를 숨길 수 있게 되었다.
- 단, 메서드 작성자가 그 메서드가 타입 안전함을 보장하는 장치이다.

### 그렇다면, 메서드가 안전한지는 어떻게 확신할 수 있을까?

#### 코드 32-2 자신의 제네릭 매개변수 배열의 참조를 노출한다. - 안전하지 않음
이 메서드가 반환하는 배열 타입은 메서드에 인수를 넘기는 컴파일타임에 결정되는데, 그 시점에 컴파일러에게 충분한 정보가 주어지지 않아 타입을 잘못 판단할 수 있다. 따라서 varargs 매개변수 배열을 그대로 반환하면 힙 오염을 이 메서드를 호출한 쪽의 콜스택으로까지 전이하는 결과를 낳을 수 있다.
```java
static<T> T[] toArray(T.. args) {
    return args
}
```

#### 코드 32-3 제네릭 varargs 매개변수를 안전하게 사용하는 메서드
```java
@SafeVarargs
static<T> List<T> flatten(List<? extends T>... lists) {
    List<T> result = new ArrayList<>();
    for (List<? extends T> list : lists)
        result.addAll(list);
    return result;
}
```

### @SafeVarargs 애너테이션
- 재정의 할 수 없는 메서드에만 선언한다. (재정의가 가능하면 안전을 보장할 수 없다.)
- 자바 8 에서는 오직 static 메서드와 final 인스턴스에서만 사용할 수 있고. 자바 9 부터는 private 인스턴스 메서드에도 허용된다.

### 코드 32-4 제네릭 varargs 매개변수를 List로 대체한 예 - 타입 안전하다.
```java
static<T> List<T> flatten(List<List<? extends T>> lists) {
    List<T> result = new ArrayList<>();
    for (List<? extends T> list : lists)
        result.addAll(list);
    return result;
}
```

### 정리
- 가변인수와 제네릭은 궁합이 좋지 않다. (가변인수 기능은 배열을 노출하여 추상화가 완벽하지 못하고, 배열과 제네릭의 타입 규칙이 서로 다르기 때문)
- 메서드에 제네릭 varargs 매개변수를 사용하려거든, 그 메서드가 타입 안전한지 확인한 다음 `@SafeVarargs` 애너테이션을 달아 불편함이 없게끔 하자.









