package bounedbuffer;

public class Main {

    public static void main(String[] args) {
        
        BufferQueue buffer = new BufferQueue(5);
        
        Thread t1 = new Thread(() -> { // Producer
            Thread.currentThread().setName("Producer 1");
            for (int i = 0; i < 10; i++) {
                buffer.add(i);
            }
        });
        
        Thread t2 = new Thread(() -> { // Producer
            Thread.currentThread().setName("Producer 2");
            for (int i = 10; i < 15; i++) {
                buffer.add(i);
            }
        });
        
        Thread t3 = new Thread(() -> { // Consumer
            Thread.currentThread().setName("Consumer 1");
            for (int i = 0; i < 5; i++) {
                buffer.remove();
            }
        });
        
        Thread t4 = new Thread(() -> { // Consumer
            Thread.currentThread().setName("Consumer 2");
            for (int i = 0; i < 5; i++) {
                buffer.remove();
            }
        });
        
        Thread t5 = new Thread(() -> { // Consumer
            Thread.currentThread().setName("Consumer 3");
            for (int i = 0; i < 5; i++) {
                buffer.remove();
            }
        });
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        
    }
    
}
