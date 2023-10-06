package effective.item78;

public class VolatileProblem {
    private volatile int count = 0;

    public static void main(String[] args) {
        VolatileProblem example = new VolatileProblem();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                example.increment();
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                example.increment();
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Count: " + example.getCount());
    }

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
