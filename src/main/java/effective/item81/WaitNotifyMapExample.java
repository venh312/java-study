package effective.item81;

import java.util.HashMap;
import java.util.Map;

public class WaitNotifyMapExample {
    public static void main(String[] args) {
        final Map<String, Integer> map = new HashMap<>();
        final Object lock = new Object();

        // Producer 스레드
        Thread producer = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Producer 스레드: 작업을 시작합니다.");
                map.put("A", 1);
                lock.notify(); // 대기 중인 Consumer 스레드를 깨움
                System.out.println("Producer 스레드: 작업을 완료했습니다.");
            }
        });

        // Consumer 스레드
        Thread consumer = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Consumer 스레드: 작업을 시작합니다.");
                try {
                    while (!map.containsKey("A")) {
                        lock.wait(); // Producer 스레드가 데이터를 추가할 때까지 대기
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Consumer 스레드: 작업을 완료했습니다.");
                System.out.println("Value for key A: " + map.get("A"));
            }
        });

        producer.start();
        consumer.start();
    }
}
