package ProducerConsumer;

/**
 * Created by Николай on 11.04.2016.
 */
public class Producer implements Runnable {
    private CustomQueue<Product> queue;

    public Producer(CustomQueue<Product> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            int number = 0;
            while (true) {
                Product prod = new Product(++number);
                System.out.println("Producer is waiting for adding product № " + number + ".");
                queue.put(prod);
                Thread.sleep((long)(Math.random() * 500));
                System.out.println("Producer has added product № " + number + ".");
            }
        } catch (InterruptedException e) {
            return;
        }
    }
}
