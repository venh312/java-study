## ITEM 14 - Comparable을 구현할지 고려하라.

### Comparable 인터페이스란?
- Comparable 인터페이스는 객체를 정렬하는데 사용되는 메서드인 compareTo를 정의하고 있다.
- Comparable 인터페이스를 구현한 클래스는 `반드시 compareTo를 정의`해야 한다.

### Comparable 인터페이스 특징
- 자바에서 같은 타입의 인스턴스를 비교해야만 하는 클래스들은 모두 Comparable 인터페이스를 구현하고 있다.
- Boolean 타입을 제외한 [래퍼 클래스](https://github.com/conf312/concept-description/blob/master/Java/%EB%9E%98%ED%8D%BC%ED%81%B4%EB%9E%98%EC%8A%A4.md)와 알파벳, 연대같이 순서가 명확한 클래스들은 모두 정렬이 가능하다.
- 기본 정렬 순서는 작은 값에서 큰 값으로 정렬되는 오름차순이다.

### Comparable 인터페이스 구현
- Comparable 을 구현했다는 것은 그 클래스의 인스턴스들에는 자연적인 순서가 있음을 의미한다.

### Comparable 인터페이스의 compareTo 메서드
- compareTo 는 해당 객체와 전달된 객체의 순서를 비교한다.
- compareTo는 Object의 euqals와 두가지 차이점이 있다. compareTo는 equals와 달리 단순 동치성에 더해 순서까지 비교할 수 있으며, 제네릭하다.
- Comparable을 구현했다는 것은 그 클래스의 인스턴스에 자연적인 순서가 있음을 뜻한다. 예를 들어, Comparable을 구현한 객체들의 배열은 Arrays.sort(a)로 쉽게 정렬이 가능하다.

### compareTo 메서드 일반규약
- 이 객체와 주어진 객체의 순서를 비교한다. 객체가 주어진 객체보다 `작으면 음의정수`를 `같으면 0`을, `크면 양의 정수`를 반환한다. 비교할 수 없는 타입이 주어지면 `ClassCastException`을 던진다.

### 대칭성
- 두 객체참조의 순서를 바꿔 비교해도 예상한 결과가 나와야한다.
- Comparable을 구현한 클래스는 모든 x, y에 대하여 sgn(x.compareTo(y)) == -sgn(y.compareTo(x))여야 한다. 따라서 x.compareTo(y)는 y.compareTo(x)가 예외를 던질때에 한해 예외를 던져야 한다.

### 추이성
- 첫 번째가 두번째보다 크고 두 번째가 세번째보다 크면 첫번째는 세번째보다 커야한다.
- Comparable을 구현한 클래스는 추이성을 보장해야 한다. 즉, (x.compareTo(y)>0 && y.compareTo(z) > 0)이면 x.compareTo(z) > 0 이다.

### 반사성
- 크기가 같은 객체들끼리는 어떤 객체와 비교하더라도 항상 같다.
- Comparable을 구현한 클래스는 모든 z에 대해 x.compareTo(y) == 0이면 sgn(x.compareTo(z)) == sgn(y.compareTo(z))이다.

### equals
- compareTo 메서드로 수행한 동치성테스트 결과가 equals와 같아야 한다.
- (x.compareTo(y) == 0) == (x.equals(y))여야 한다. Comparable을 구현하고 이 권고를 지키지 않는 모든 클래스는 그 사실을 명시해야 한다. (주의: 이 클래스의 순서는 equals 메서드와 일관되지 않다.)







