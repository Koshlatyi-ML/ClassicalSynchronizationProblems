package ProducerConsumer;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Created by Николай on 25.04.2016.
 */
public class CustomQueue<E> {
    private Semaphore full;
    private Semaphore empty;
    private final LinkedList<E> queue = new LinkedList<>();

    public CustomQueue(Semaphore full, Semaphore empty) {
        this.full = full;
        this.empty = empty;
    }

    public void put(E e) throws InterruptedException {
        full.acquire();
        empty.release();
        queue.add(e);
    }

    public E take() throws InterruptedException {
            empty.acquire();
            full.release();
            return queue.removeFirst();
    }
}
