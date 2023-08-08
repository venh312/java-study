package effective.item12;

/*
아이템12 - toString을 항상 재정의하라.
 */
public class Main {
    public static void main(String[] args) {
        Product product = new Product();
        product.setName("DESK");
        product.setType("A");

        /*
        클래스@16진수로 표시한 해시코드
        effective.item12.Product@251a69d7

        toString() 재정의
        Product(name=DESK, type=A)
         */
        System.out.println(product);
    }
}
