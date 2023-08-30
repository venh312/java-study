## ITEM 33 - 타입 안전 이종 컨테이너를 고려하라.

### 이종 안전 컨테이너 패턴 (type safe heterogeneous container pattern)
- 컨테이너에 값을 넣거나 뺄 때 매개변수화한 키를 함께 제공한다.
- 이렇게 하면 제네릭 타입 시스템이 값의 타입이 키와 같음을 보장해줄 것이다.

### 코드 33-1
- 이 방식이 동작하는 이유는 class 의 클래스가 제네릭이기 때문이다.
- class 의 리터럴 타입은 Class 가 아닌 Class<T> 이다.
- String.class 타입은 Class<String>, Integer.class 타입은 Class<Integer>