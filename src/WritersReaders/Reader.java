package WritersReaders;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Николай on 11.04.2016.
 */
public class Reader implements Runnable {

    private AtomicInteger writers;
    private AtomicInteger readers;
    private AtomicInteger writeRequests;

    private int id;
    private Storage<Message> storage;

    public Reader(int id, Storage<Message> storage,
                  AtomicInteger writers, AtomicInteger readers, AtomicInteger writeRequests) {
        this.id = id;
        this.storage = storage;
        this.writers = writers;
        this.readers = readers;
        this.writeRequests = writeRequests;
    }

    public synchronized void lockRead() throws InterruptedException {
        while(writers.get() > 0 || writeRequests.get() > 0) {
            wait();
        }
        readers.incrementAndGet();
    }

    public synchronized void unlockRead() {
        readers.decrementAndGet();
        notifyAll();
    }

    @Override
    public void run() {
        try {
            while (true) {
                lockRead();
                System.out.println("Reader-" + id + " is waiting for his turn.");
                try {
                    Message mess = storage.read();
                    unlockRead();
                    System.out.println("Reader-" + id + " has read message № " + mess.getId() + ".");
                    Thread.sleep((long) (Math.random() * 150));
                } catch (NoSuchElementException e) {
                    System.out.println("Storage is empty!!!");
                } finally {
                    unlockRead();
                }
            }
        } catch (InterruptedException e) {
            return;
        } finally {
            unlockRead();
        }
    }
}
