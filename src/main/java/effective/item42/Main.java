package effective.item42;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] words = {"A", "B", "D", "E", "F", "C"};
        List<String> list = new ArrayList<>(Arrays.asList(words));
        // 코드 42-1 익명 클래스의 인스턴스를 함수 객체로ㅓ 사용 - 낡은 기법이다.
//        Collections.sort(list, new Comparator<String>(){
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });
//        System.out.println(String.join(", ", list));

        // 코드 42-2 람다식을 함수 객체로 사용 - 익명 클래스 대체
//        Collections.sort(list, (o1, o2) -> o1.compareTo(o2));
//        System.out.println(String.join(", ", list));
//
//        // 비교자 생성 메서드를 사용하면 더 간결하게 만들 수 있다.
//        Collections.sort(list, String::compareTo);
//
//        // List 인터페이스에 추가된 sort 메서드를 이용하면 더욱 짧아진다.
//        list.sort(String::compareTo);

//        double sum1 = Operation.PLUS.apply(1,2);
//        double sum2 = OperationLambda.PLUS.apply(1,2);
//
//        System.out.println("sum1: " + sum1);
//        System.out.println("sum2: " + sum2);
    }
}
