## ITEM 65 - 리플렉션보다는 인터페이스를 사용하라.

### 리플렉션 기능(ljava.lang.reflect) 을 이용하면 프로그램에서 임의의 클래스에 접근할 수 있다.
리플렉션(Reflection)은 프로그램이 자신의 구조를 분석하고 클래스, 메서드, 필드 등의 정보를 동적으로 조사하고 조작할 수 있는 기능을 말합니다. 

이는 `런타임`(Runtime) 시에 클래스의 정보를 살펴보거나 수정할 수 있게 해주는 기능으로 

일반적으로 `컴파일` 타임(Compile Time)에는 알 수 없는 클래스나 메서드에 대한 작업을 수행할 때 사용됩니다.

### 리플렉션을 이용하면 컴파일 당시에 존재하지 않던 클래스도 이용할수 있지만, 단점이 있다.
1. 컴파일타임 타입 검사가 주는 이점을 하나도 누릴 수 없다.
2. 리플렉션을 이용하면 코드가 지저분하고 장황해진다.
3. 성능이 떨어진다.

### 그렇다면 언제 사용할까? `코드 분석 도구`나 `의존관계 주입 프레임워크`와 같은 곳에서 사용하기는 하나..
이런 도구들마저 리플렉션 사용을 점차 줄이고 있다. `단점`이 명백하기 때문이다.

### <코드> 65-1
```java
private static void fatalError(String msg) {
    System.out.println(msg);
    System.exit(1);
}

public static void main(String[] args) {
    // 클래스 이름을 Class 객체로 변환
    Class<? extends Set<String>> cl = null;

    try {
        cl = (Class<? extends Set<String>>) Class.forName(args[0]); // 비검사 형변환
    } catch (ClassNotFoundException e) {
        fatalError("클래스를 찾을 수 없습니다.");
    }

    // 생성자를 얻는다.
    Constructor<? extends Set<String>> cons = null;

    try {
        cons = cl.getDeclaredConstructor();
    } catch (NoSuchMethodException e) {
        fatalError("매개변수 없는 생성자를 찾을 수 없습니다.");
    }

    // 집합의 인스턴스를 만든다.
    Set<String> s = null;

    try {
        s = cons.newInstance();
    } catch (InvocationTargetException e) {
        fatalError("생성자가 예외를 던졌습니다." + e.getCause());
    } catch (InstantiationException e) {
        fatalError("클래스를 인스턴스화 할 수 없습니다.");
    } catch (IllegalAccessException e) {
        fatalError("생성자에 접근할 수 없습니다.");
    }

    s.addAll(Arrays.asList(args).subList(1, args.length));
    System.out.println(s);
}
```
1. 리플렉션은 아주 제한된 형태로만 사용해야 그 단점을 피하고 이점만 취할수 있다.
2. 리플렉션은 인스턴스 생성에만 쓰고, 이렇게 만든 인스턴스는 인터페이스나 사우이 클래스로 참조해 사용하자.

### 정리
- `리플렉션`은 복잡한 특수 시스템을 개발할 떄 필요한 강력한 기능이지만, 단점도 많다.
- `컴파일 타임`에는 알 수 없는 클래스를 사용하는 프로그램을 작성한다면 `리플렉션`을 사용해야 할 것이다.
- 단, 되도록 `객체 생성`에만 사용하고, 생성한 객체를 이용 할 때는 적절한 인터페이스나 `컴파일 타임`에 알 수 있는 상위 클래스로 형변환해 사용해야 한다.