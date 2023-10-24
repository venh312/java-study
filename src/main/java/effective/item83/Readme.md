## ITEM 83 - 지연 초기화는 신중히 사용하라.

### 지연 초기화 (lazy initialization)
필드의 초기화 시점을 그 값이 처음 필요할때까지 늦추는 기법으로 값이 쓰이지 않으면 초기화도 결코 일어나지 않는다.

### 지연 초기화는 양날의 검
클래스 혹은 인스턴스 생성 시의 초기화 비용은 줄지만, 지연 초기화하는 필드에 접근하는 비용은 커진다.

#### 그렇다면 지연 초기화가 필요할때는?
클래스 혹은 인스턴스 중 그 필드를 사용하는 인스턴스의 비율이 낮은 반면, 그 필드를 초기화하는 비용이 큰 경우 (이 경우도 확실한 측정을 해야 한다.)

### 멀티스레드 환경에서는 지연 초기화를 하기가 까다롭다.
대부분의 상황에서 일반적인 초기화가 지연 초기화 보다 낫다.

### <코드 83-1> 인스턴스 필드를 초기화하는 일반적인 방법
```java
private final FieldType field = computeFieldValue();
```

### <코드 83-2> 인스턴스 필드 지연 초기화 - synchronized 접근자 방식
- 지연 초기화가 초기화 순환성을 깨뜨릴 것 같으면 사용하는 방식이다.
```java
private FieldType field;

private synchronized FieldType getField() {
    if (field == null)
        field = computeFieldValue();
    return field;
}
```

### <코드 83-3> 정적 필드용 지연 초기화 홀더 클래스 관용구
- 성능 때문에 정적 필드를 지연 초기화 해야한다면 지연 초기화 홀더 클래스 관용구를 사용하자. (클래스가 처음 쓰일 때 초기화 된다는 특성을 이용한 관용구)
```java
private static class FieldHolder {
    static final FieldType field = computeFieldVlaie();   
}

private static FieldType getField() {
    return FieldHolder.field;
}
```

### <코드 83-4> 인스턴스 필드 지연 초기화 이중검사 관용구
- 성능 때문에 인스턴스 필드를 지연 초기화 해야한다면 이중검사 관용구를 사용하라.
```java
private volatile FieldType field;

private FieldType getField() {
    FieldType result = field;
    if (result != null) // 첫번째 검사 (락 사용 안 함)
        return result;
    
    synchronized(this) {
        if (field == null) // 두번째 검사 (락 사용)
            field = computeFieldValue();
        return field;
    }
}
```

- @ManyToOne, @OneToOne과 같이 @XXXToOne 어노테이션들은 기본이 즉시 로딩(EAGER) 이다.
- @OneToMany와 @ManyToMany는 기본이 지연 로딩(LAZY)이다.

### 정리
- 대부분의 필드는 지연시키지 말고 바로 초기화해야 한다.
- 성능 때문에 초기화 순환을 막기 위해 지연 초기화 해야 한다면 올바른 초기화 기법을 사용하자.















