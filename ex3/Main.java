package ex3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int weeks = 2;
        Ebook ebook = new Ebook();

        Runnable r = new Runnable() {
            public void run() {
                (new Thread(new Professor("Professor", Arrays.asList("IT", "IP", "IK"), ebook, weeks))).start();
                (new Thread(new Professor("Assistant 0", Arrays.asList("IT", "IP", "IK"), ebook, weeks))).start();
                (new Thread(new Professor("Assistant 1", Arrays.asList("IT", "IP", "IK"), ebook, weeks))).start();
                (new Thread(new Professor("Assistant 2", Arrays.asList("IT", "IP", "IK"), ebook, weeks))).start();
            }
        };

        Thread thread = new Thread(r);
        thread.start();
        thread.join();
        ebook.displayMarks();
    }
}