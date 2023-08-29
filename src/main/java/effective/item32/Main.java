package effective.item32;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    // 코드 32-1
    //@SuppressWarnings("unchecked")
    static void dangerous(List<String>... list) {
        Integer[] intArr = {1,2,3};
        List<Integer> intList = Arrays.asList(intArr);
        Object[] objects = list;
        objects[0] = intList;   // 힙 오염 발생
        String s = list[0].get(0);  // ClassCastException
    }

    public static void main(String[] args) {
        List<String> stringList1 = new ArrayList<>();
        List<String> stringList2 = new ArrayList<>();
        dangerous(stringList1, stringList2);

        // 코드 28-3
//        List<String>[] list = new List<String>[1];
//        Integer[] intArr = {1,2,3};
//        List<Integer> intList = Arrays.asList(intArr);
//        Object[] objects = list;
//        objects[0] = intList;
//        String s = list[0].get(0);
    }
}
