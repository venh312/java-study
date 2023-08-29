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
