package effective.item61;

import java.util.Comparator;

public class Main {
    // 코드 61-2 문제를 수정한 비교자
    static void clearNaturalOrder() {
        Comparator<Integer> naturalOrder = (o1, o2) -> {
            int i = o1, j = o2;
            return (i < j) ? -1 : (i == j ? 0 : 1);
        };
        System.out.println(naturalOrder.compare(new Integer(42), new Integer(42)));
    }

    // 코드 61-3 기이하게 동작하는 프로그램 - 결과를 맞혀보자.
    static Integer i ;
    static void unbelievable() {
        if (i == 42)
            System.out.println("믿을 수 없군!");
    }

    public static void main(String[] args) {
        // 코드 61-1
        //Comparator<Integer> naturalOrder = (o1, o2) -> (o1 < o2) ? -1 : (o1 == o2 ? 0 : 1);
        //System.out.println(naturalOrder.compare(1,2));
        //System.out.println(naturalOrder.compare(new Integer(42), new Integer(42)));

        // 코드 61-2
        //clearNaturalOrder();

        // 코드 61-3
        //unbelievable();

        // 코드 61-4
        long startTime = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println(endTime + " ms");
    }
}
