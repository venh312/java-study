## ITEM 16 - public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라.

```java
public class Point {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
```
- getter / setter 로 내부표현 변경이 가능하다.
- 클라이언트는 public 메서드를 통해서만 데이터 접근이 가능하다.
- 외부에서 부수작업을 수행시킬 수 있다.
- package-private 클래스 혹은 private 중첩 클래스 데이터 캡슐화
- package-private 클래스 혹은 private 중첩 클래스라면 데이터 필드를 노출한다 해도 클래스가 표현하려는 추상 개념만 올바르게 표현해주면 문제가 없다.
