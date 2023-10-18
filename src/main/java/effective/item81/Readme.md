## ITEM 81 - wait 와 notify 보다는 동시성 유틸리티를 애용하라.

### wait 와 notify 는 올바르게 사용하기 까다로우니 고수준 동시성 유틸리티를 사용하자.

### `wait` 와 `notify` 는 스레드 간 동기화를 달성하기 위한 메서드이다.

### `java.util.concurrent` 의 고수준 유틸리티
- 실행자 프레임워크 (Executor) 아이템80
- 동시성 컬렉션 (concurrent)
- 동기화 장치 (synchronize)

### 정리
- wait 와 notify 를 직접 사용하는 것을 동시성 어셈블리 언어로 프로그래밍하는 것에 비유할 수 있다.
- java.util.concurrent 는 고수준 언어에 비유할 수 있다.
- 따라서, 새로 작성한다면 wait 와 notify 를 쓸 이유가 없다.
- 레거시 코드를 유지보수해야 한다면, wait 는 표준 관용구에 따라 while 문 안에서 호출하고, notify 보다는 notifyAll 을 사용해야한다. (응답불가 상태 방지를 위해)



