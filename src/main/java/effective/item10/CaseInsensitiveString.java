package effective.item10;

import java.util.Objects;

public final class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString(String s) {
        /*
        주로 메서드나 생성자의 파라미터로 전달된 값이 null일 경우 예외 처리를 명시적으로 수행하기 위해 사용
         */
        this.s = Objects.requireNonNull(s);
    }

    // 대칭성 위배 x.equals(y) != y.equals(x)
    @Override
    public boolean equals(Object o) {
        if (o instanceof CaseInsensitiveString)
            return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
        if (o instanceof String)
            return s.equalsIgnoreCase((String) o);
        return false;
    }

    public static void main(String[] args) {
        String testA = "aaa"; // 문자열 리터럴 JVM에 HEAP String Pool
        String testB = "aaa";
        String testC = new String("aaa");
        System.out.println(testA == testB);
        System.out.println(testA == testC);
        System.out.println(testA.equals(testB));
        System.out.println(testA.equals(testC));
        System.out.println(System.identityHashCode(testA));
        System.out.println(System.identityHashCode(testB));
        System.out.println(System.identityHashCode(testC));
    }
}
