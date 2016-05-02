package WritersReaders;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Николай on 11.04.2016.
 */
public class Writer implements Runnable {

    private AtomicInteger writers;
    private AtomicInteger readers;
    private AtomicInteger writeRequests;

    private int id;
    private Storage<Message> storage;

    public Writer(int id, Storage<Message> storage,
                  AtomicInteger writers, AtomicInteger readers, AtomicInteger writeRequests) {
        this.id = id;
        this.storage = storage;
        this.writers = writers;
        this.readers = readers;
        this.writeRequests = writeRequests;
    }


    public synchronized void lockWrite() throws InterruptedException {
        writeRequests.incrementAndGet();
        while(readers.get() > 0 || writers.get() > 0) {
            wait();
        }
        writeRequests.decrementAndGet();
        writers.incrementAndGet();
    }

    public synchronized void unlockWrite() {
        writers.decrementAndGet();
        notifyAll();
    }

    @Override
    public void run() {
        try {
            int number = 0;
            while (true) {
                Message mess = new Message(id + "." + ++number);
                System.out.println("Writer-" + id + " is waiting for sending message № " + mess.getId() + ".");
                lockWrite();
                storage.write(mess);
                unlockWrite();
                System.out.println("Writer-" + id + " has sent message № " + mess.getId() + ".");
                Thread.sleep((long) (Math.random() * 500));
            }
        } catch (InterruptedException e) {
            return;
        } finally {
            unlockWrite();
        }
    }
}
