package bounedbuffer;

import java.util.concurrent.Semaphore;

public class BufferQueue {

    int[] array;
    int size;
    int in;
    int out;

    Semaphore mutex;
    Semaphore full;
    Semaphore empty;
    
    public BufferQueue(int size) { // constructor
        this.size = size;
        array = new int[size];
        mutex = new Semaphore(1);
        full = new Semaphore(0);
        empty = new Semaphore(size);
        in = 0;
        out = 0;
    }

    public void add(int ItemToAdd) { // Produce

        // wait(empty)
        try {
            empty.acquire();
        } catch (InterruptedException ex) {
        }

        // wait(mutex)
        try {
            mutex.acquire();
        } catch (InterruptedException ex) {
        }

        // Critical Section
        array[in] = ItemToAdd;
        in = (in + 1) % size;
        System.out.println("\u001B[32m" + Thread.currentThread().getName() + " Added " + ItemToAdd); // "\u001B[32m" Just for coloring
        Main.list.add(Thread.currentThread().getName() + " Added " + ItemToAdd);
        
        // signal(mutex)
        mutex.release();

        // signal(full)
        full.release();

    }

    public int remove() { // Consume

        int RemovedItem;

        // wait(full)
        try {
            full.acquire();
        } catch (InterruptedException ex) {
        }

        // wait(mutex)
        try {
            mutex.acquire();
        } catch (InterruptedException ex) {
        }

        // Critical Section
        RemovedItem = array[out];
        out = (out + 1) % size;
        System.out.println(Thread.currentThread().getName() + " Removed " + RemovedItem);
        Main.list.add(Thread.currentThread().getName() + " Removed " + RemovedItem);

        // signal(mutex)
        mutex.release();

        // signal(empty)
        empty.release();

        return RemovedItem;

    }

}
