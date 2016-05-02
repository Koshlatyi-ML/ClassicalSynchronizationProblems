package EatingPhilosophers;

import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Николай on 12.04.2016.
 */
public class Philosopher implements Runnable {
    private int index;
    private ReentrantLock leftForkLock;
    private ReentrantLock rightForkLock;
    private ForkList forks;

    private Fork leftFork;
    private Fork rightFork;

    public Philosopher(int index, ReentrantLock leftForkLock, ReentrantLock rightForkLock, ForkList forks) {
        this.index = index;
        this.leftForkLock = leftForkLock;
        this.rightForkLock = rightForkLock;
        this.forks = forks;
    }

    public void think() throws InterruptedException {
        long thinkingTime = (long) (Math.random() * 500);
        System.out.println("Philosopher-" + index + " has began thinking.");
        Thread.sleep(thinkingTime);
        System.out.println("Philosopher-" + index + " has been thinking for " + thinkingTime + " ms.");
    }

    public void eat() throws InterruptedException {
        long mealtime = (long) (Math.random() * 500);
        System.out.println("Philosopher-" + index + " is eating.");
        Thread.sleep(mealtime);
        System.out.println("Philosopher-" + index + " has been eating for " + mealtime + " ms.");
    }

    @Override
    public void run() {
        boolean isLeftAcquired = false;
        boolean isRightAcquired = false;
            while (true) {
                try {
                    System.out.println("Philosopher-" + index + " waiting for left fork");
                    leftFork = forks.tryTakeLeft(index);
                    isLeftAcquired = true;
                    System.out.println("Philosopher-" + index + " has taken left fork.");

                    System.out.println("Philosopher-" + index + " waiting for right fork");
                    rightFork = forks.takeRight((index + 1) % 5);
                    isRightAcquired = true;
                    System.out.println("Philosopher-" + index + " has taken right fork.");

                    eat();

                    forks.giveBack(index);
                    isLeftAcquired = false;
                    forks.giveBack((index + 1) % 5);
                    isRightAcquired = false;

                    think();
                } catch (InterruptedException e) {
                    if(isLeftAcquired)
                        forks.giveBack(index);
                    if(isRightAcquired)
                        forks.giveBack((index + 1) % 5);
                    return;
                } catch (NoSuchElementException e) {
                    System.out.println(index + " NoSuch!!!");
                }
            }
    }
}
