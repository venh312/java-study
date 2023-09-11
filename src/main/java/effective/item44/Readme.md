## ITEM 44 - 표준 함수형 인터페이스를 사용하라.

### 필요한 용도에 맞는 게 있다면, 직접 구현하지 말고 표준 함수형 인터페이스를 활용하라.
`java.util.function` 패키지를 보면 다양한 용도의 표준 함수형 인터페이스가 담겨있다. 해당 패키지에는 `총 43개`의 인터페이스가 담겨있다.

### 기본 인터페이스 6개만 기억하면 나머지를 충분히 유추해낼 수 있다.
<table><thead><tr><th>인터페이스</th><th>함수 시그니처</th><th>예</th></tr></thead><tbody><tr><td><code>UnaryOperator&lt;T&gt;</code></td><td><code>T apply(T t)</code></td><td><code>String::toLowerCase</code></td></tr><tr><td><code>BinaryOperator&lt;T&gt;</code></td><td><code>T apply(T t1, T t2)</code></td><td><code>BigInteger::add</code></td></tr><tr><td><code>Predicate&lt;T&gt;</code></td><td><code>boolean test(T t)</code></td><td><code>Collection::isEmpty</code></td></tr><tr><td><code>Function&lt;T&gt;</code></td><td><code>R apply(T t)</code></td><td><code>Arrays::asList</code></td></tr><tr><td><code>Supplier&lt;T&gt;</code></td><td><code>T get()</code></td><td><code>Instant::now</code></td></tr><tr><td><code>Consumer&lt;T&gt;</code></td><td><code>void accept(T t)</code></td><td><code>System.out::println</code></td></tr></tbody></table>

- Operator 인터페이스는 인수가 1개인 UnaryOperator와 2개인 BinaryOperator로 나뉘며, 반환값과 인수의 타입이 같은 함수를 뜻한다.
- Predicate 인터페이스는 인수 하나를 받아 boolean을 반환하는 함수를 뜻한다.
- Function 인터페이스는 인수와 반환 타입이 다른 함수를 뜻한다.
- Supplier 인터페이스는 인수를 받지 않고 값을 반환(혹은 제공)하는 함수를 뜻한다.
- Consumer 인터페이스는 인수를 하나 받고 반환값은 없는, 인수를 소비하는 함수를 뜻한다.

### 언제 표준 함수형 인터페이스를 사용해야 할까?
- 표준 함수형 인터페이스 대부분은 기본 타입만 지원한다. 그렇다고 기본 함수형 인터페이스에 박싱된 기본 타입을 넣어 사용하지는 않도록 한다.`계산량이 많을 때는 성능이 처참히 느려질 수 있다.`
- 표준 인터페이스 중 필요한 용도에 맞는 게 없다면 직접 작성해야 하며, 구조적으로 똑같은 표준 함수형 인터페이스가 있더라도 직접 작성해야만 할 때가 있다.

Comparator<T> 인터페이스의 경우, 구조적으로 ToIntBiFunction<T, U>와 동일하지만 독자적인 인터페이스로 존재해야 하는 이유가 몇 개 있다.

- 첫 번째. API에서 굉장히 자주 사용되는데, 이름이 그 용도를 아주 훌륭히 설명해준다.
- 두 번째. 구현하는 쪽에서 반드시 지켜야 할 규약을 담고 있다.
- 세 번째. 비교자들을 변환하고 조합해주는 유용한 디폴트 메서드들을 많이 담고 있다.
Comparator의 특성을 정리하면 아래와 같다. 이 중 하나 이상을 만족한다면 전용 함수형 인터페이스를 구현해야 하는 건 아닌지 고민해보도록 해야 한다.

자주 쓰이며, 이름 자체가 용도를 명확히 설명해준다.
반드시 따라야 하는 규약이 있다.
유용한 디폴트 메서드를 제공할 수 있다.
만약 전용 함수형 인터페이스를 작성하기로 했다면, '인터페이스'임을 명심해야 한다. 아주 주의해서 설계해야 한다.

### @FunctionalInterface
이 애너테이션을 사용하는 이뉴는 @Override를 사용하는 이유와 비슷하다. 프로그래머의 의도를 명시하는 것으로, 크게 세 가지 목적이 있다.

- 첫 번째. 해당 클래스의 코드나 설명 문서를 읽을 이에게 그 인터페이스가 람다용으로 설계된 것임을 알려준다.
- 두 번째. 해당 인터페이스가 추상 메서드를 오직 하나만 가지고 있어야 컴파일되게 해준다. 
- 세 번째. 유지보수 과정에서 누군가 실수로 메서드를 추가하지 못하게 막아준다.
따라서, 직접 만든 함수형 인터페이스에는 항상 @FunctionalInterface 애너테이션을 사용하도록 한다.

## 함수형 인터페이스를 사용할 때의 주의점
서로 다른 함수형 인터페이스를 같은 위치의 인수로 받는 메서드들을 다중 정의해서는 안 된다.
클라이언트에게 불필요한 모호함만 줄 뿐이며, 이 때문에 실제로 문제가 일어나기도 한다.

### 정리
- 자바 8부터 람다를 지원한다. 입력값과 반환값에 함수형 인터페이스 타입을 활용하도록 한다.
- 보통은 java.util.function 패키지의 표준 함수형 인터페이스를 사용하는 것이 가장 좋은 선택이다.
- 가끔은 직접 새로운 함수형 인터페이스를 만들어 쓰는 편이 나을 수도 있다.
