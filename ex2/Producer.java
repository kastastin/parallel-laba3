package ex2;

import java.util.Random;

public class Producer implements Runnable {
    private final Drop drop;
  
    public Producer(Drop drop) {
         this.drop = drop;
    }
  
    @Override
    public void run() {
        int[] importantInfo = new int[100];

        for (int i = 0; i < importantInfo.length; i++) {
            importantInfo[i] = i + 1;
        } 
        Random random = new Random();
        for (int i : importantInfo) {
            drop.put(i);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        drop.put(0);
    }
}