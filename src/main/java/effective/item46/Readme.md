## ITEM 46 - 스트림에서는 부작용 없는 함수를 사용하라.

### 스트림 패러다임의 핵심은 계산을 일련의 변환으로 재구성 하는 부분이다.
- 각 변환 단계는 가능한 한 이전 단계의 결과를 받아 처리하는 순수 함수여야 한다.
- 다른 가변 상태를 참조하지 않고, 함수 스스로도 다른 상태를 변경하지 않는다.


### 코드 46-1 스트림 패러다임을 이해하지 못한 채 API만 사용했다. - 따라 하지 말 것!
```java
Map<String, Long> freq = new HashMap<>();
try (Stream<String> words = new Scanner(file).tokens()) {
    words.forEach(word -> {
        freq.merge(word.toLowerCase(), 1L, Long::sum);    
    })
}
```
스트림, 람다, 메서드 참조를 사용했고, 결과도 올바르다. 하지만 절대 스트림 코드라 할 수 없다. `스트림 코드를 가장한 반복적 코드다.`


### 코드 46-2 스트림을 제대로 활용해 빈도표를 초기화한다.
```java
Map<String, Long> freq;
try (Stream<String> words = new Scanner(file).tokens()) {
    freq = words.collect(groupingBy(String::toLowerCase, counting()));
}
```
46-1와 같은 일을 수행했고, 스트림 API를 제대로 사용 했으며 짧고 명확하다.

#### 순수함수
오직 입력만이 결과에 영향을 주는 함수

### 정리
- 스트림 파이프라인 프로그래밍의 핵심은 부작용 없는 함수 객체에 있다.
- 종단 연산 중 `forEach` 는 스트림이 수행한 계산 결과를 보고할 때만 이용해야 한다. 계산 자체에는 이용하지 말자.
- 가장 중요한 수집기(collector)는 toList, toSet, toMap, groupingBy, joining 이다.