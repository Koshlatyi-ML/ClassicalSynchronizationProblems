package WritersReaders;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Николай on 11.04.2016.
 */
public class Main {

    public static void main(String[] args) throws  InterruptedException {

        AtomicInteger readers       = new AtomicInteger(0);
        AtomicInteger writers       = new AtomicInteger(0);
        AtomicInteger writeRequests = new AtomicInteger(0);

        Storage<Message> storage = new Storage();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 3; i++) {
            executor.execute(new Writer(i + 1, storage, writers, readers, writeRequests));
        }
        for (int i = 0; i < 7; i++) {
            executor.execute(new Reader(i + 1, storage, writers, readers, writeRequests));
        }

        Thread.sleep(5000);
        executor.shutdownNow();
    }
}
