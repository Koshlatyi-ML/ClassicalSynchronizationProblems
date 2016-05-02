package WritersReaders;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Created by Николай on 11.04.2016.
 */
public class Storage<E> extends ArrayList<E> {

    public void write(E e) throws InterruptedException {
        this.add(e);
    }

    public E read() throws NoSuchElementException {
        if(this.size() == 0)
            throw new NoSuchElementException();

        Random r = new Random();
        return this.get(r.nextInt(this.size()));
    }
}
