package ProducerConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Николай on 11.04.2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        int queueSize = 10;
        Semaphore full = new Semaphore(queueSize);
        Semaphore empty = new Semaphore(0);
        CustomQueue<Product> queue = new CustomQueue<>(full, empty);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new Producer(queue));
        executor.execute(new Consumer(queue));

        Thread.sleep(10000);
        executor.shutdownNow();
    }
}
