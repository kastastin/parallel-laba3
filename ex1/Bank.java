package ex1;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {
    public static final int NTEST = 10_000;
    private final int[] accounts;
    private long ntransacts = 0;

    /*
    private final ReentrantLock locker = new ReentrantLock();
    private final AtomicIntegerArray accounts;
    private final AtomicLong ntransacts = new AtomicLong(0);
    */
    
    public Bank(int n, int initialBalance) {
        accounts = new int[n];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = initialBalance;
        }
        ntransacts = 0;
    }    

    /* Конструктор з використанням Atomic Variables
    public Bank(int n, int initialBalance) {
        accounts = new AtomicIntegerArray(new int[n]);
        for (int i = 0; i < accounts.length(); i++) {
            accounts.set(i, initialBalance);
        }
        ntransacts.set(0);
    }
    */

    public int size() { return accounts.length; }

    /* метод для визначення кількості аккаунтів
       з використанням Atomic Variables
    public int size() { return accounts.length(); }
    */

    // public void transfer(int from, int to, int amount) {
    //     accounts[from] -= amount;
    //     accounts[to] += amount;
    //     ntransacts++;
    //     if (ntransacts % NTEST == 0) test();
    // }

    /* синхронізований метод transfer
    public synchronized void transfer(int from, int to, int amount) throws InterruptedException {
        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        if (ntransacts % NTEST == 0) test();
    }
    */

    public synchronized void transfer(int from, int to, int amount) throws InterruptedException {
        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        if (ntransacts % NTEST == 0) test();
    }

    /* метод transfer з використанням ReentrantLock
    public void transfer(int from, int to, int amount) throws InterruptedException {
        locker.lock();
        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        if (ntransacts % NTEST == 0) test();
        locker.unlock();
    }
    */

    /* метот transfer з використанням Atomic Variables
    public void transfer(int from, int to, int amount) throws InterruptedException {
        accounts.addAndGet(from, -amount);
        accounts.addAndGet(to, amount);
        ntransacts.incrementAndGet();
        if (ntransacts.get() % NTEST == 0) test();
    }
    */

    public void test() {
        int sum = 0;
        for (int i = 0; i < accounts.length; i++) {
            sum += accounts[i];
        }
        System.out.printf("\nTransactions: %d  Sum: %d", ntransacts, sum);
    }

    /* метод test з використанням Atomic Variables
    public void test() {
        AtomicInteger sum = new AtomicInteger(0);
        for (int i = 0; i < accounts.length(); i++) {
            sum.addAndGet(accounts.get(i));
        }
        System.out.printf("\nTransactions: " +  ntransacts.get() + "  Sum: " + sum.get());
    }
    */
}