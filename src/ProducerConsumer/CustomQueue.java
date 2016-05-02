package ProducerConsumer;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Created by Николай on 25.04.2016.
 */
public class CustomQueue<E> {
    public Semaphore full;
    public Semaphore empty;
    private final LinkedList<E> queue = new LinkedList<>();

    public CustomQueue(Semaphore full, Semaphore empty) {
        this.full = full;
        this.empty = empty;
        // new comment
    }

    public void put(E e) throws InterruptedException {
        full.acquire();
        System.out.println("Add: full acquired!");
        empty.release();
        System.out.println("Add: full released!");
        queue.add(e);
    }

    public E take() throws InterruptedException {
            empty.acquire();
            System.out.println("Remove: empty acquired!");
            full.release();
            System.out.println("Remove: full released!");
            return queue.removeFirst();
    }
}
