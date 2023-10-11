package effective.item79;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        ObservableSet<Integer> observableSet = new ObservableSet<>(new HashSet<>());

        observableSet.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23)
                    set.removeObserver(this); // 문제 발생
            }
        });

        for (int i = 0; i < 100; i++)
            observableSet.add(i);
    }
}
