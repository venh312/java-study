package effective.item78;

import java.util.concurrent.TimeUnit;

public class Main {
    private static boolean stopRequested;

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    private static synchronized boolean stopRequested() {
        return stopRequested;
    }

    /*
    이는 JVM 이 실제로 적용하는 끌어올리기(hoisting, 호이스팅)라는 최적화 기법이 사용된 것이다.
    결과적으로 응답 불가(liveness failure) 상태가 되어 더 이상 진행되는 코드가 없다.
    다시 기존 코드로 돌아와서 생각해보면, 공유하는 변수를 다룰 때 동기화하는 코드를 넣으면 된다.

    이처럼 동기화는 읽기와 쓰기에 대해 모두 필요하다. 아래 코드처럼 공유 필드에 대한 읽기/쓰기 메서드 모두를 동기화 처리하면 문제는 해결된다.
     */
    static void exampleMethod() throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested)
//            while (!stopRequested())
                i++;
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
//        requestStop();
    }

    public static void main(String[] args) throws InterruptedException {
        exampleMethod();
    }
}
