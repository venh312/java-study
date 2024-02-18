package study.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingBy {
    public static void main(String[] args) {
        List<Order> list = List.of(
            new Order("후라이드 치킨", 17000, "Andrew"),
            new Order("양념 치킨", 18000, "Tom"),
            new Order("피자", 18000,  "Key"),
            new Order("돈가스", 10000,  "Andrew"),
            new Order("초밥", 13000, "Andrew")
        );

        Map<String, List<Order>> collect = list.stream().collect(Collectors.groupingBy(Order::getOrderBy));
        collect.forEach((length, itemList) -> System.out.println("orderBy : " + length + " | Item :" + itemList));
    }
}
