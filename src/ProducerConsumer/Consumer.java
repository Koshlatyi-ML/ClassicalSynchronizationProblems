package ProducerConsumer;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Created by Николай on 11.04.2016.
 */
public class Consumer implements Runnable {
    private Semaphore access;
    private CustomQueue<Product> queue;

    public Consumer(Semaphore access, CustomQueue<Product> queue) {
        this.access = access;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Consumer is waiting for his turn.");
                try {
                    access.acquire();
                    Product prod = queue.take();
                    Thread.sleep((long) (Math.random() * 500));
                    System.out.println("Consumer has gotten product № " + prod.getNum() + ".");
                } finally {
                    access.release();
                }
            }
        } catch (InterruptedException e) {
            return;
        } finally {
            access.release();
        }

    }
}
