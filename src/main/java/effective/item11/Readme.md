논리적으로 같은 객체는 같은 해시코드를 반환해야 한다.

예)
```java
Map<PhoneNumber, String> m = new HashMap<>();
m.put(new PhoneNumber(707, 876, 5309), "제니");
```
이 코드에
```java
m.get(new PhoneNumber(707, 876k 5309));
```
를 실행하면 `제니`가 나와야 할 같지만 null을 반환한다.

hashCode를 재정의 하지 않았기 때문에 논리적 동치인 두 객체가 서로 다른 해시코드를 반환한다.

```java
@Override public int hashCode() { return 42; };
```
이 코드는 모든 객체에서 똑같은 해시코드를 반환하니 적법하다. `하지만` 모든 객체에게 똑같은 값만 내어 주므로
모든 객체가 해시 테이블의 버킷 하나에 담겨 마치 연결 리스트(linked list) 처럼 동작한다.

그 결과 평균 수행 시간이 O(1)인 해시테이블이 O(n)으로 느려져 도저히 사용할 수 없게 된다.

따라서 아래와 같은 코드로 hashCode를 재정의 하게 된다.   
고정된 31 숫자는 홀수이면서 소수(prime)이기 때문이다.
```java
@Override public int hashCode() { return 31 * Short.hashCode(필드); };
```

### 정리
equals를 재정의 할때는 hashCode도 반드시 재정의해야 한다.
lombok의 @EqualsAndHashCode는 이 과정을 어노테이션으로 제공한다.












