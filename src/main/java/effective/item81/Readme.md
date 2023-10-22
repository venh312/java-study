## ITEM 81 - wait 와 notify 보다는 동시성 유틸리티를 애용하라.

### wait 와 notify 는 올바르게 사용하기 까다로우니 고수준 동시성 유틸리티를 사용하자.

### `wait` 와 `notify` 는 스레드 간 동기화를 달성하기 위한 메서드이다.

### `java.util.concurrent` 의 고수준 유틸리티
- 실행자 프레임워크 (Executor) 아이템80
- 동시성 컬렉션 (concurrent)
- 동기화 장치 (synchronize)

### `wait`와 `notify`를 직접 사용하는 대신 `java.util.concurrent` 패키지의 concurrent 컬렉션의 이점

1. **더 높은 수준의 추상화**: `java.util.concurrent` 패키지의 concurrent 컬렉션은 스레드 간 동기화를 단순화하고 추상화합니다. 이로써 스레드 간 동기화 관리가 더 쉽고 안전하게 이루어집니다.

2. **더 높은 성능**: concurrent 컬렉션은 내부적으로 효율적인 동기화 메커니즘을 사용하여 스레드 간 경합을 줄이고 성능을 향상시킵니다. `wait`와 `notify`는 고전적인 동기화 메커니즘으로 경합과 데드락 가능성이 있습니다.

3. **편리한 메서드**: concurrent 컬렉션은 보다 편리한 메서드를 제공하며, 예를 들어, `putIfAbsent()`, `replace()`, `compute()` 등의 메서드를 사용하여 복잡한 동기화 작업을 단순화할 수 있습니다.

4. **스레드 안전한 반복**: concurrent 컬렉션은 반복 중에 컬렉션을 수정하거나 동시에 다른 스레드가 수정하더라도 안전하게 반복할 수 있는 메서드를 제공합니다.

5. **확장 가능성**: `java.util.concurrent` 패키지에는 다양한 concurrent 컬렉션 종류가 있으며, 다양한 상황에 맞게 선택할 수 있습니다. 예를 들어, `ConcurrentHashMap`, `ConcurrentSkipListMap`, `ConcurrentLinkedQueue` 등이 있습니다.

6. **동시성을 처리하기 위한 라이브러리**: `java.util.concurrent` 패키지에는 스레드 풀, 동시성 유틸리티, `ExecutorService`, `ForkJoinPool` 등과 같이 스레드 간 동시성 문제를 다루기 위한 다양한 도구와 라이브러리도 제공됩니다.

즉, `java.util.concurrent` 패키지의 concurrent 컬렉션을 사용하면 스레드 간 동기화 문제를 더 효과적으로 처리할 수 있고, 코드의 안정성과 가독성을 향상시킬 수 있습니다. 그래서 가능하다면 `java.util.concurrent` 패키지의 concurrent 컬렉션을 사용하는 것이 좋습니다.

### 정리
- wait 와 notify 를 직접 사용하는 것을 동시성 어셈블리 언어로 프로그래밍하는 것에 비유할 수 있다.
- java.util.concurrent 는 고수준 언어에 비유할 수 있다.
- 따라서, 새로 작성한다면 wait 와 notify 를 쓸 이유가 없다.
- 레거시 코드를 유지보수해야 한다면, wait 는 표준 관용구에 따라 while 문 안에서 호출하고, notify 보다는 notifyAll 을 사용해야한다. (응답불가 상태 방지를 위해)



