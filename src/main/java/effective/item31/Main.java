package effective.item31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        StackGeneric<Number> stackGeneric = new StackGeneric<>();
        Integer[] intArr = {1,2,3,4,5,7};
        Iterable<Integer> integers = new ArrayList<>(Arrays.asList(intArr));
        /*
            Integer는 Number의 하위 타입이니 잘 동작 해야 할 것 같다.
            하지만, 매개변수화 타입이 불공변이기 때문에 오류 메시지가 뜬다.
        */
        // 에러 발생! incompatible types: Iterable<Integer> cannot be converted to Iterable<Number>
//        stackGeneric.pushAll(integers);

        /*
            해결책 - 한정적 와일드카드 타입
            E의 Iterable 이 아니라 E의 하위 타입의 Iterable 이어야 한다.
        */
//        stackGeneric.pushAllWildcard(integers);

        // 스택의 원소를 Object 용 컬렉션으로 옮기려 한다.
        //
//        Collection<Object> objects = new ArrayList<>();
//        stackGeneric.popAll(objects);

        // E의 상위 타입의 Collection 이어야 한다.(모든 타입은 자기 자신의 상위 타입이다.)
//        stackGeneric.popAllSuper(objects);

        Integer max = Arrays.stream(intArr).max(Integer::compare).get();
        System.out.println("intArr sum : " + max);
    }

    public static void swap(List<?> list, int i, int j) {
        // 에러 발생! incompatible types: int cannot be converted to CAP#1
        // 우리는 이전에 와일드카드 타입에는 null 외에 아무 값도 넣을수 없다고 배웠다.
        //list.set(i, list.set(i, list.get(i)));

        // 형변환을 거치지 않고 private 메서드로 내부에서 숨겨 도우미 메서드로 따로 작성하여 사용한다.
        // 다소 번거롭다고 느낄 수 없지만, 외부에서는 와일드카드 기반의 멋진 선언을 유지하며, swapHelper 의 존재를 모른채 그 혜택을 누리는 것이다.
        //swapHelper(list, i, j);
    }

    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }
}
