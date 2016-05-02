package BarberShop;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Николай on 13.04.2016.
 */
public class CustomQueue extends LinkedList<Client> {
    ReentrantLock lock = new ReentrantLock();

    private AtomicInteger capacity;

    public CustomQueue(AtomicInteger capacity) {
        this.capacity = capacity;
    }

    public boolean isFilled() {
        lock.lock();
        try {
            if (this.size() == capacity.get())
                return true;
            else {
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return super.size();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        try {
            return super.isEmpty();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean add(Client client) {
        lock.lock();
        try {
            return super.add(client);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Client removeFirst() {
        lock.lock();
        try {
            return super.removeFirst();
        } finally {
            lock.unlock();
        }
    }
}
