package effective.item80;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Thread thread = new Thread(() -> {
            System.out.println("Thread Call : " + Thread.currentThread().getName());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread Call : " + Thread.currentThread().getName());
        });

        thread.start();
        thread2.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable!");
            }
        };

        executor.execute(runnable);

        Future<String> future = executor.submit(() -> {
            // 비동기로 실행할 코드
            return "태스크 완료1 : " + Thread.currentThread().getName();
        });

        Future<String> future2 = executor.submit(() -> {
            // 비동기로 실행할 코드
            return "태스크 완료2 : " + Thread.currentThread().getName();
        });

        Future<String> future3 = executor.submit(() -> {
            // 비동기로 실행할 코드
            return "태스크 완료3 : " + Thread.currentThread().getName();
        });

        Future<String> future4 = executor.submit(() -> {
            // 비동기로 실행할 코드
            return "태스크 완료4 : " + Thread.currentThread().getName();
        });

        try {
            String result = future.get();
            System.out.println(result);

            String result2 = future2.get();
            System.out.println(result2);

            String result3 = future3.get();
            System.out.println(result3);

            String result4 = future4.get();
            System.out.println(result4);

            int poolSize = ((ThreadPoolExecutor) executor).getPoolSize();
            System.out.println("현재 스레드 풀 크기: " + poolSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}

