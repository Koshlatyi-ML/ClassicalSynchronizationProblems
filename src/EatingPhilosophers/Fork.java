package EatingPhilosophers;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Николай on 12.04.2016.
 */
public class Fork {
    private boolean isTaken;
    private boolean isLeft;
    private boolean isRight;
    ReentrantLock lock = new ReentrantLock();

    public Fork() {
        isTaken = false;
        isLeft = false;
        isRight = false;
    }

    public boolean isTaken() {
        lock.lock();
        try {
            if (isTaken)
                return true;
            else
                return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean isLeft() {
        lock.lock();
        try {
            if (isLeft)
                return true;
            else
                return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean isVacant() {
        lock.lock();
        try {
            if (!isTaken() || isTaken() && !isLeft())
                return true;
            else
                return false;
        } finally {
            lock.unlock();
        }
    }

    public void setLeft() {
        lock.lock();
        isLeft = true;
        isRight = false;
        lock.unlock();
    }

    public void setRight() {
        lock.lock();
        isLeft = false;
        isRight = true;
        lock.unlock();
    }

    public void unset() {
        lock.lock();
        isLeft = false;
        isRight = false;
        lock.unlock();
    }

    public void setTaken() {
        lock.lock();
        isTaken = true;
    }

    public void setVacant() {
        isTaken = false;
        lock.unlock();
    }
}
