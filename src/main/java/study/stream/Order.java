package study.stream;

public class Order {
    private String itemName;     //주문아이템 이름
    private Integer amount;      //주문 금액
    private String orderBy;      //주문자 이름

    public Order(String itemName, Integer amount, String orderBy) {
        this.itemName = itemName;
        this.amount = amount;
        this.orderBy = orderBy;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getOrderBy() {
        return orderBy;
    }

    @Override
    public String toString() {
        return "Order{" +
                "itemName='" + itemName + '\'' +
                ", amount=" + amount +
                ", orderBy='" + orderBy + '\'' +
                '}';
    }
}
