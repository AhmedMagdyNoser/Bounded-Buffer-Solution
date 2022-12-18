package bounedbuffer;

import java.awt.*;
import javax.swing.*;

public class Main {
    
    static List list = new List();
    
    static int PN = 0;
    static int CN = 0;

    public static void main(String[] args) throws InterruptedException {
        
        // GUI
        
        JFrame frame = new JFrame();
        frame.setTitle("Bounded Buffer");
        frame.setVisible(true);
        frame.setSize(500, 650);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Frame to the center
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setLayout(null);
        frame.add(panel);
        
        JLabel label = new JLabel("The Processes");
        label.setForeground(Color.white);
        label.setBounds(200, 0, 100, 35);
        
        JLabel producers = new JLabel();
        producers.setForeground(Color.white);
        producers.setBounds(50, 550, 150, 35);
        
        JLabel consumers = new JLabel();
        consumers.setForeground(Color.white);
        consumers.setBounds(290, 550, 150, 35);
        
        panel.add(label);
        panel.add(producers);
        panel.add(consumers);
        panel.add(list);
        
        list.setBounds(25, 35, 435, 500);
        
        BufferQueue buffer = new BufferQueue(5);
        
        Thread t1 = new Thread(() -> { // Producer
            PN++;
            Thread.currentThread().setName("Producer 1");
            for (int i = 0; i < 10; i++) {
                buffer.add(i);
            }
        });
        
        Thread t2 = new Thread(() -> { // Producer
            PN++;
            Thread.currentThread().setName("Producer 2");
            for (int i = 10; i < 15; i++) {
                buffer.add(i);
            }
        });
        
        Thread t3 = new Thread(() -> { // Consumer
            CN++;
            Thread.currentThread().setName("Consumer 1");
            for (int i = 0; i < 5; i++) {
                buffer.remove();
            }
        });
        
        Thread t4 = new Thread(() -> { // Consumer
            CN++;
            Thread.currentThread().setName("Consumer 2");
            for (int i = 0; i < 5; i++) {
                buffer.remove();
            }
        });
        
        Thread t5 = new Thread(() -> { // Consumer
            CN++;
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
        
        Thread.sleep(1000);
        
        producers.setText("Number of Producers : " + String.valueOf(PN));
        consumers.setText("Number of Consumers : " + String.valueOf(CN));
        
    }
    
}
