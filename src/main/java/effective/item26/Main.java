package effective.item26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void unsafeAdd(List list, Object o) {
        list.add(o);
    }

    public static void main(String[] args) {
//        List stamps = new ArrayList<>();
//        stamps.add(new Coin());
//        Stamp stamp = (Stamp) stamps.get(0);
//        System.out.println(stamp);

        // 매개변수화된 컬렉션 타입 - 안전성 확보
//        List<Stamp> stamps2 = new ArrayList<>();
//        stamps2.add(new Coin());

//        List<Integer> list = new ArrayList<>();
//        unsafeAdd(list, "aaa");
//        System.out.println("list.get(0) : " + list.get(0)); // 런타임시 확인이 가능하다.

//        Integer[] arr = {1,2,3,4,5};
//        String[] arrString = {"a","b","c","d","e"};
//        List<?> wildcard = Arrays.asList(arr);
//        System.out.println(wildcard.get(0));
    }
}
