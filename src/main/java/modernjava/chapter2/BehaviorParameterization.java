package modernjava.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class BehaviorParameterization {
    static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list)
            if (p.test(e))
                result.add(e);
        return result;
    }

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                                    new Apple(Color.GREEN, 150),
                                    new Apple(Color.RED, 200),
                                    new Apple(Color.GREEN, 120));

        List<Apple> redApples = filter(inventory, (Apple apple) -> apple.getColor().equals(Color.RED));
        redApples.stream().forEach(apple -> System.out.println(apple.getColor())); // RED

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
        evenNumbers.stream().forEach(integer -> System.out.print(integer.toString() + " ")); // 2 4 6 8 10
        System.out.println();
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                System.out.println("o1.getWeight(" + o1.getWeight() + ") - o2.getWeight(" + o2.getWeight() + ") : " + (o1.getWeight() - o2.getWeight()));
                return o1.getWeight() - o2.getWeight();
            }
        });

        inventory.forEach(apple -> System.out.print(apple.getWeight() + " "));
    }
}

enum Color {
    RED, GREEN
}

class Apple {
    private Color color;
    private int weight;

    public Apple(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }
}

