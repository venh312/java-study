package effective.item31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        StackGeneric<Number> stackGeneric = new StackGeneric<>();
        Integer[] intArr = {1,2,3,4,5};
        Iterable<Integer> integers = new ArrayList<>(Arrays.asList(intArr));
        /*
            Integer는 Number의 하위 타입이니 잘 동작 해야 할 것 같다.
            하지만, 매개변수화 타입이 불공변이기 때문에 오류 메시지가 뜬다.
        */
//        stackGeneric.pushAll(integers);

        /*
            해결책 - 한정적 와일드카드 타입
            E의 Iterable 이 아니라 E의 하위 타입의 Iterable 이어야 한다.
            Iterable<? extends E>
        */
        stackGeneric.pushAllWildcard(integers);

        // 스택의 원소를 Object 용 컬렉션으로 옮기려 한다.
//        Collection<Object> objects = new ArrayList<>();
//        stackGeneric.popAllCollection(objects);

        // E의 상위 타입의 Collection 이어야 한다.(모든 타입은 자기 자신의 상위 타입이다.)
//        stackGeneric.popAllCollectionSuper(objects);


        
    }
}
