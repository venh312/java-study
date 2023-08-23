package effective.item31;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        StackGeneric<Number> stackGeneric = new StackGeneric<>();
        Iterable<Integer> integers = new ArrayList<>();
        // 매개변수화 타입이 불공변이기 때문에 오류 메시지가 뜬다.
        stackGeneric.pushAll(integers);

        //stackGeneric.pushAllWildcard(integers);
    }
}
