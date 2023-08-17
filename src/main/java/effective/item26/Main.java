package effective.item26;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void unsafeAdd(List list, Object value) {

    }
    public static void main(String[] args) {
        List stamps = new ArrayList<>();
        stamps.add(new Coin());
        Stamp stamp = (Stamp) stamps.get(0);
        System.out.println(stamp);

        // 매개변수화된 컬렉션 타입 - 안전성 확보
//        List<Stamp> stamps2 = new ArrayList<>();
//        stamps2.add(new Coin());


    }
}
