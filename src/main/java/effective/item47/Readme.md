## ITEM 47 - 반환 타입으로는 스트림보다 컬렉션이 낫다.

### 반복
스트림은 반복(iteration)을 지원하지 않는다. 따라서 스트림과 반복을 알맞게 조합해야 좋은 코드가 나온다.

### Stream 인터페이스는 Iterable 인터페이스가 정의한 추상 메서드를 전부 포함하며, Iterable 인터페이스가 정의한 방식대로 동작한다.
그럼에도 for-each 로 스트림을 반복할 수 없는 이유는 `Stream` 이 `Iterable` 을 확장(extend) 하지 않아서다.

### Collection 인터페이스는 Iterable 의 하위 타입이고 stream 메서드도 제공한다.
Collection 인터페이스는 반복과 스트림을 동시에 지원한다. 따라서 원소 시퀀스를 반환하는 공개 API의 반환 타입에는 Collection 이나 그 하위 타입을 쓰는 게 일반적으로 최선이다.

### 스트림을 반환하는 방법
#### 입력 리스트의 모든 부분리스트를 스트림으로 반환한다.
```java
    public static <E> Stream<List<E>> of(List<E> list) {
        return Stream.concat(Stream.of(Collections.emptyList()), prefixes(list).flatMap(SubLists::suffixes));
    }

    private static <E> Stream<List<E>> prefixes(List<E> list) {
        return IntStream.rangeClosed(1, list.size())
                .mapToObj(end -> list.subList(0, end));
    }

    private static <E> Stream<List<E>> suffixes(List<E> list) {
        return IntStream.range(0, list.size())
                .mapToObj(start -> list.subList(start, list.size()));
    }
```

### 정리
- 원소 시퀀스를 반환하는 메서드를 작성할 때는, 이를 스트림으로 처리하기를 원하는 사용자와 반복으로 처리하길 원하는 사용자가 모두 있을 수 있음을 떠올리고, 양쪽을 만족시키려 노력하자.
- 컬렉션을 반환할 수 있다면 그렇게하라.
- 반환 전부터 이미 원소들을 컬렉션에 담아 관리하고 있거나, 컬렉션을 하나 더 만들어도 될 정도로 원소 개수가 적다면 `ArrayList` 같은 표준 컬렉션에 담아 반환하라.
- 컬렉션을 반환하는 게 불가능하다면 `Stream` 과 `Iterable` 중 자연스러운 것을 반환하라. (만약 나중에 Stream 인터페이스가 Iterable을 지원하도록 자바가 수정 된다면, 그때는
안심하고 스트림을 반환하면 될 것이다.)