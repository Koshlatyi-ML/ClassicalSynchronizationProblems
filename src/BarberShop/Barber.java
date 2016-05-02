package BarberShop;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Николай on 12.04.2016.
 */
public class Barber extends Thread {
    private ReentrantLock lock = new ReentrantLock();
    public Condition isSleeping = lock.newCondition();

    private CustomQueue barberSeat;
    private CustomQueue waitingRoom;

    public Barber(CustomQueue barberSeat, CustomQueue waitingRoom) {
        this.barberSeat = barberSeat;
        this.waitingRoom = waitingRoom;
    }

    public void wakeUp() {
        lock.lock();
        isSleeping.signal();
        lock.unlock();
    }

    public void fallAsleep() throws InterruptedException {
        lock.lock();
        isSleeping.await();
        lock.unlock();
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (waitingRoom.isEmpty()) {
                    System.out.println("Barber has fall asleep.");
                    fallAsleep();
                }
                Client client = waitingRoom.removeFirst();
                client.call();
                System.out.println("Barber has called the client.");
                barberSeat.add(client);
                Thread.sleep(500);
                barberSeat.removeFirst();
                client.serve();
                System.out.println("Barber has haircutted client.");
            }
        } catch (InterruptedException e) {
            return;
        }
    }
}
