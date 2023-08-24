package effective.item31;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        StackGeneric<Number> stackGeneric = new StackGeneric<>();
        Iterable<Integer> integers = new ArrayList<>();
        // 매개변수화 타입이 불공변이기 때문에 오류 메시지가 뜬다.
        //stackGeneric.pushAll(integers);
      
        /*
            해결책 - 한정적 와일드카드 타입
            E의 Iterable 이 아니라 E의 하위 타입의 Iterable 이어야 한다.
            Iterable<? extends E>
        */
        //stackGeneric.pushAllWildcard(integers);
    }
}
