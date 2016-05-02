package BarberShop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Николай on 12.04.2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        CustomQueue waitingRoomQueue = new CustomQueue(new AtomicInteger(5));
        CustomQueue barberRoomSeat = new CustomQueue(new AtomicInteger(1));

        ExecutorService executor = Executors.newFixedThreadPool(101);
        Barber b = new Barber(barberRoomSeat, waitingRoomQueue);
        executor.execute(b);
        for (int i = 0; i < 100; i++) {
            executor.execute(new Client(b, waitingRoomQueue));
            Thread.sleep(100);
        }
        Thread.sleep(10000);
        executor.shutdownNow();
    }
}
