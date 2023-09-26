## ITEM 62 - 다른 타입이 적절하다면 문자열 사용을 피하라.

### 문자열의 혼용
문자열(String)은 텍스트를 표현하게 설계되었지만 의도하지 않은 용도로 사용되기도 한다.

### 문자열은 다른 값 타입을 대신하기에 적합하지 않다.
데이터가 수치형이라면 `int`, `float` 등을 사용하고 예/아니오 형태라면 `enum`, `boolean`을 사용하면 좋다. 다룰 데이터가 정말로 문자열인 경우에만 사용하는 것이 좋다. 적절한 타입이 없다면 새로 하나 정의하는 것도 좋다.

### 문자열은 열거 타입을 대신하기에 적합하지 않다.
문자열로 상수를 열거할 때는 `enum` 타입이 월등히 낫다.

### 문자열은 혼한 타입을 대신하기에 적합하지 않다.
혼합 타입을 대신하기에도 적합하지 않다.

```java
String compoundKey = className + "#" + i.next();
```

### 문자열은 권한을 표현하기에 적합하지 않다.
권한(capacity)를 문자열로 표현하는 경우가 종종 있다. 문자열로 키를 사용하는 예제이다.

### <코드> 62-2 잘못된 예 - 문자열을 사용해 권한을 구분하였다.
```java
public class ThreadLocal {
    private ThreadLocal() { } // 객체 생성 불가

    // 현재 스레드의 값을 키로 구분해 저장한다.
    public static void set(String key, Object value);

    // 키가 가리키는 현재 스레드의 값을 반환한다.
    public static Object get(String key);
}
```

### <코드> 62-5 매개변수화하여 타입안정성 확보
```java
public final class ThreadLocal<T> {
    public ThreadLocal();
    public void set(T value);
    public T get();
}
```

### 정리
- 더 적합한 데이터 타입이 있거나 작성할 수 있다면, 문자열을 쓰고 싶은 유혹을 뿌리쳐라.
- 문자열은 잘못 사용하면 번거롭고, 유연하지 못하고, 느리고, 오류 가능성도 크다.
- 문자열을 잘못 사용하는 예로 기본 타입, 열거 타입, 혼합 타입이 있다.