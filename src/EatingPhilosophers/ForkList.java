package EatingPhilosophers;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Николай on 12.04.2016.
 */
public class ForkList extends ArrayList<Fork> {
    ReentrantLock lock = new ReentrantLock();

    public Fork tryTakeLeft(int index) throws NoSuchElementException, InterruptedException {

        int vacantCount = 0;
        lock.lock();
        for (Fork f : this) {
            if (f.isVacant())
                vacantCount++;
        }
        lock.unlock();

        if (vacantCount < 2) {
            throw new NoSuchElementException();
        }

        this.get(index).setTaken();
        this.get(index).setLeft();
        return this.get(index);
    }

    public Fork takeRight(int index) throws InterruptedException {
        this.get(index).setTaken();
        this.get(index).setRight();
        return this.get(index);
    }

    public void giveBack(int index) {
        this.get(index).unset();
        this.get(index).setVacant();
    }

}
