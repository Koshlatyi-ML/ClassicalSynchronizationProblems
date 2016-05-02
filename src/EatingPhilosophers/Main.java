package EatingPhilosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Николай on 12.04.2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock leftLock = new ReentrantLock(true);
        ReentrantLock rightLock = new ReentrantLock(true);

        ForkList forks = new ForkList();
        for (int i = 0; i < 5; i++) {
            forks.add(new Fork());
        }

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executor.execute(new Philosopher(i, leftLock, rightLock, forks));
        }

        Thread.sleep(10000);
        executor.shutdownNow();
    }
}
