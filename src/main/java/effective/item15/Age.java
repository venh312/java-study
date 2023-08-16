package effective.item15;

public class Age {
    public static final int AGE = 30;

    //public static final int[] AGE_ARR = { 10, 20, 30 };

    // 배열을 private 만들고 그 복사본을 반환하는 public 메서드를 제공하는 방법이다. (방어적 복사)
    private static final int[] AGE_ARR = { 10, 20, 30 };
    public static final int[] values() {
        return AGE_ARR.clone();
    }
}
