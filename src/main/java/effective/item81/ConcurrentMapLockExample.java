package effective.item81;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapLockExample {
    public static void main(String[] args) {
        ConcurrentMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();

        String key = "myKey";

        // Key에 대한 잠금을 시뮬레이트하기 위한 객체
        Object lock = new Object();

        // 스레드 1
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                int value = concurrentMap.getOrDefault(key, 0); // 기본값 0
                value += 1;
                concurrentMap.put(key, value);
            }
        });

        // 스레드 2
        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                int value = concurrentMap.getOrDefault(key, 0);
                value += 2;
                concurrentMap.put(key, value);
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("결과: " + concurrentMap.get(key));
    }
}

