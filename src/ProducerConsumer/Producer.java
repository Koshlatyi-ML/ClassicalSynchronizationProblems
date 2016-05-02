package ProducerConsumer;

import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Created by Николай on 11.04.2016.
 */
public class Producer implements Runnable {
    private Semaphore access;
    private CustomQueue<Product> queue;

    public Producer(Semaphore semaphore, CustomQueue<Product> queue) {
        this.access = semaphore;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            int number = 0;
            while (true) {
                Product prod = new Product(++number);
                System.out.println("Producer is waiting for adding product № " + number + ".");
                access.acquire();
                queue.put(prod);
                Thread.sleep((long)(Math.random() * 500));
                System.out.println("Producer has added product № " + number + ".");
                access.release();
            }
        } catch (InterruptedException e) {
            return;
        } catch (ArrayStoreException e) {
            System.out.println("Queue is overflowed!!!");
        } finally {
            access.release();
        }
    }
}
