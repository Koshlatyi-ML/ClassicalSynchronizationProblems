package BarberShop;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Николай on 12.04.2016.
 */
public class Client extends Thread {
    private ReentrantLock lock = new ReentrantLock();
    private Condition isWaiting = lock.newCondition();
    private Condition isHaircutting= lock.newCondition();

    private CustomQueue waitingRoom;
    private Barber barber;
    public Client(Barber barber, CustomQueue waitingRoom) {
        this.barber = barber;
        this.waitingRoom = waitingRoom;
    }

    public void call() {
        lock.lock();
        isWaiting.signal();
        lock.unlock();
    }
    public void serve() {
        lock.lock();
        isHaircutting.signal();
        lock.unlock();
    }

    public void startWaiting() throws InterruptedException {
        lock.lock();
        isWaiting.await();
        lock.unlock();
    }

    public void getServing() throws InterruptedException {
        lock.lock();
        isHaircutting.await();
        lock.unlock();
    }

    @Override
    public void run() {
        try {
            if(waitingRoom.isFilled()) {
                System.out.println("Queue is fullfilled, client has left.");
                return;
            }

            waitingRoom.add(this);
            System.out.println("Client has joined to queue.");
            barber.wakeUp();

            startWaiting();
            getServing();
            return;
        } catch (InterruptedException e) {
            return;
        }
    }

}
