package effective.item30;

import java.util.HashSet;
import java.util.Set;
import java.util.function.UnaryOperator;

public class Main {
    // UnaryOperator - Java 8부터 java.util.function 패키지에 속해 제공
    // 함수형 인터페이스(Functional Interface) 중 하나로, 입력값 하나를 받아서 연산을 수행한 후 결과값을 반환하는 기능을 가진 함수
    private static final UnaryOperator<Object> IDENTITY_FN = (t) -> t + " 테스트";

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    static Set union(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }

    public static <E> Set<E> unionGeneric(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    public static void main(String[] args) {
        String[] strings = {"에이","비","씨"};
        UnaryOperator<String> sameString = identityFunction();
        for (String s : strings)
            System.out.println(sameString.apply(s));

        Integer[] integers = {1,2,3};
        UnaryOperator<Integer> sameInteger = identityFunction();
        for (Integer i : integers)
            System.out.println(sameInteger.apply(i));


    }
}
