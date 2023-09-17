## ITEM 48 - 스트림 병렬화는 주의해서 적용하라.
`자바 7` 부터는 고성능 병렬 분해 (parallel decom-position) 프레임워크인 포크-조인(fork-join) 패키지를 추가했다.

`자바 8` 부터는 parallel 메서드만 한 번 호출하면 파이프라인을 병렬 실행할 수 있는 스트림을 지원했다.

자바로 동시성 프로그램을 작성하기가 쉬워지고 있지만, 올바르고 빠르게 작성하는 일은 여전히 어려운 일이다. 동시성 프로그래밍을 할 때는 
`안전성(safety)`과 `응답 가능(liveness)` 상태를 유지하기 위해 애써야 한다.


### 코드 48-1 스트림을 사용해 처음 20개의 메르센 소수를 생성하는 프로그램
```java
public static void main(String[] args) {
   primes().map(p -> TWO.pos(p.intValueExact()).subtract(ONE))
      .fileter(mersenne -> mersenne.isProbablePrime(50))
      .limit(20)
      .forEach(System.out::println);
}

static Stream<BigInteger> primes() {
   return Stream.iterate(TWO, BigInteger::nextProbablePrime);
}
```
해당 프로그램을 실행하면 12.5초 만에 완료된다. 속도를 높이고 싶어서 스트림 파이프라인의 parallel() 을 호출 했다.

### 결과는 어떻게 됐을까?
아무것도 출력하지 못하면서 CPU는 90%나 잡아먹는 상태가 무한히 계속된다. (응답 불가:liveness failure)

### 원인
- 스트림 라이브러리가 이 파이프라인을 병렬화 하는 방법을 찾아내지 못했다.
- 데이터 소스가 `Stream.iterate` 거나 중간 연산으로 limit를 쓰면 파이프라인 병렬화로는 성능 개선을 기대할 수 없다.

### ❗️ 파이프라인 병렬화는 limit를 다룰때 CPU 코어가 남는다면 원소를 몇 개 더 처리한 후 제한된 개수 이후의 결과를 버려도 아무런 해가 없다고 가정한다.

## 스트림 병렬화 효과를 기대할 수 있는 경우
### 1. 스트림의 소스가 ArrayList, HashMap, HashSet, ConcurrentHashMap의 인스턴스거나 배열, int 범위, long 범위 일때

#### 1) 데이터의 분할이 정확하고 쉽다.
- 이 자료구조들은 모두 데이터를 원하는 크기로 정확하고 손 쉽게 나눌 수 있어서 `다수의 스레드에게 분배`하기 좋다.
- `Spliterator` 가 나누는 작업을 담당하며, Stream 이나 Iterable 의 spliterator 메서드로 얻어올 수 있다.

#### 2) 참조 지역성 (locality of reference)이 뛰어나다.
- 이웃한 원소의 참조들이 메모리에 연속해서 저장되어 있기 때문이다.
- 참조 지역성은 다량의 데이터를 처리하는 벌크 연산을 병렬화할 때 아주 중요한 요소로 작용한다
- 참조 지역성이 가장 뛰어난 자료구조는 기본 타입의 배열이다. (데이터 자체가 메모리에 연속해서 저장되어 있기 때문에)

#### 참조 지역성이 낮으면 스레드는 데이터가 주 메모리에서 캐시 메모리로 전송되어 오기를 기다리며 대부분 시간을 낭비한다.

### 2. 종단 연산이 병렬화에 적합할 때
- 축소(파이프라인에서 만들어진 모든 원소를 합치는 작업) 연산이 가장 병렬화에 적합하다
- Stream 의 `reduce` 메서드 중 하나, 혹은 `min`, `max`, `count`, `sum` 같이 완성된 형태로 제공되는 메서드 중 하나
- `anyMatch`, `allMatch`, `noneMatch` 처럼 조건에 맞으면 바로 반환되는 메서드
- 가변 축소(mutable reduction)를 수행하는 Stream의 collect 메서드는 병렬화에 적합하지 않다. (컬렉션들을 합치는 부담이 크기 때문에)

### 정리
- 계산, 성능에 대한 확신 없이는 스트림 파이프라인을 병렬화 시도조차 하지 말자.
- 병렬화하는 편이 낫다고 믿더라도, 여전히 정확한지 확인하고 운영 환경과 유사한 조건에서 수행해보며 성능 지표를 관찰하자.
- 2가지 모두 만족 했을때만 병렬화 버전 코드를 운영 코드에 반영하라.