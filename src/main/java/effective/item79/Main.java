package effective.item79;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        ObservableSet<Integer> observableSet = new ObservableSet<>(new HashSet<>());

        observableSet.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.print(element + " ");
                /*
                ConcurrentException 발생
                동기화된 컬렉션 observers 에 대해서 반복문을 실행하는 중에 removeObserver 로 컬렉션 내부의 값을 삭제 하려하기 때문에
                */
                if (element == 23)
                    set.removeObserver(this); // 문제 발생
            }
        });

        for (int i = 1; i <= 100; i++)
            observableSet.add(i);
    }
}
