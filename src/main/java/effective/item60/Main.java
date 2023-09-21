package effective.item60;

import java.math.BigDecimal;

public class Main {

    // 코드 60-3 정수 타입을 사용한 해법
    static void code60_3() {
        int itemBought = 0;
        int funds = 100;
        for (int price = 10; funds >= price; price+=10) {
            funds -= price;
            itemBought++;
        }
        System.out.println(itemBought + "개 구입");
        System.out.println("잔돈(센트): " + funds);
    }


    public static void main(String[] args) {
        // 0.61 을 예상하지만..
        System.out.println(1.03 - 0.42);

        // 0.1 을 예상하지만..
        System.out.println(1.0 - 9 * 0.10);

        // BigDecimal 을 사용한 예
        BigDecimal bigDecimal = new BigDecimal("1.03");
        System.out.println(bigDecimal.subtract(new BigDecimal("0.42")));

        code60_3();
    }
}
