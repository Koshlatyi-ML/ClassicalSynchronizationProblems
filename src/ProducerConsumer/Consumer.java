package ProducerConsumer;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Created by Николай on 11.04.2016.
 */
public class Consumer implements Runnable {
    private CustomQueue<Product> queue;

    public Consumer(CustomQueue<Product> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Consumer is waiting for his turn.");
                Product prod = queue.take();
                Thread.sleep((long) (Math.random() * 500));
                System.out.println("Consumer has gotten product № " + prod.getNum() + ".");
            }
        } catch (InterruptedException e) {
            return;
        }
    }
}
