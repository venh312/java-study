## ITEM 58 - 전통적인 for 문 보다는 for-each 문을 사용하라.

### 코드 58-1 컬렉션 순회하기 - 더 나은 방법이 있다.
```java
for (Iterator<Element> i = c.iterator(); i.hasNext();) {
    Element e = i.next();
}
```

### 코드 58-2 배열 순회하기. 
```java
for (int i = 0; i < a.length; i++) {
        ...
}
```

### 발견된 문제점
- 반복되는 반복자, 인덱스 변수를 사용함으로써 잘못 사용할 틈새가 넓어진다.
- 잘못된 변수를 사용했을 때 컴파일러가 잡아주리라는 보장도 없다.
- 컬렉션이냐 배열이냐에 따라 코드 형태가 달라지므로 주의해야 한다.

### 향상된 for 문 (enhanced for statement) 이다.
- 반복자와 인덱스 변수를 사용하지 않으니 코드가 깔끔해지고 오류가 날 일도 없다.
- 하나의 관용구로 컬렉션과 배열을 처리할 수 있어서 신경쓰지 않아도 된다.

### 코드 58-3 컬렉션과 배열을 순회하는 올바른 관용구
```java
for (Element e : elements) {
        ...    
}
```

### 코드 58-4 버그를 찾아보자.
```java
enum Suit { CLUB, DIAMOND, HEART }
enum Rank { ACE, DEUCE, THREE, FOUR, FIVE, SIX }

static Collection<Suit> suits = Arrays.asList(Suit.values());
static Collection<Rank> ranks = Arrays.asList(Rank.values());

List<Card> deck = new ArrayList<>();
for (Iterator<Suit> i = suits.iterator(); i.hasNext();)
    for (Iterator<Rank> j = ranks.iterator(); j.hasNext();)
        deck.add(new Card(i.next(), j.next()));
```

### 코드 58-5 같은 버그, 다른 증상!
```java
enum Rank { ACE, DEUCE, THREE, FOUR, FIVE, SIX }

Collection<Rank> ranks = EnumSet.allOf(Rank.class);

List<Card> deck = new ArrayList<>();
for (Iterator<Rank> i = ranks.iterator(); i.hasNext();)
    for (Iterator<Rank> j = ranks.iterator(); j.hasNext();)
        deck.add(new Card(i.next(), j.next()));
```

### 코드 58-7 컬렉션이나 배열의 중첩 반복을 위한 권장 관용구
```java
for (Suit suit : suits)
    for (Rank rank : ranks)
        deck.add(new Card(suit, rank));
```

### for-each 문을 사용할 수 없는 3가지 상황
- 파괴적인 필터링 (destructive filtering): 컬렉션을 순회화면서 선택된 원소를 제거해야 한다면 반복자의 `remove` 메서드를 호출해야 한다. 
자바 8 부터는 `removeIf` 메서드를 사용해 컬렉션을 명시적으로 순회하는 일을 피할수 있다.
- 번형 (transforming): 리스트나 배열을 순회화면서 그 원소의 값 일부 혹은 전체를 교체해야 한다면 리스트의 `반복자`나 배열의 `인덱스`를 사용해야 한다.
- 병렬 반복 (parallel iterator): 여러 컬렉션을 병렬로 순회해야 한다면 각자의 반복자나 인덱스 변수를 사용해 `엄격`하고 `명시적`으로 제어해야 한다.

### 원소들의 묶음을 표현하는 타입을 작성해야 한다면
- Iterable 인터페이스를 구현하는 쪽으로 고민해보자.
- Iterable 을 구현해두면 그 타입을 사용하는 프로그래머가 for-each 문을 사용할 떄마다 감사해할 것이다.

### 정리
- 전통적인 for 문과 비교했을 때 `for-each` 문은 명료하고, 유연하고, 버그를 예방해준다. 
- 성능저하도 없다. 가능한 모든 곳에서 `for-each` 문을 사용하자.









### 정리
