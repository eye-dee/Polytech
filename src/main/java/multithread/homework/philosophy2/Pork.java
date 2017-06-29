package multithread.homework.philosophy2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Polytech
 * Created on 27.06.17.
 */
public class Pork implements Lock {
    private Lock mutex = new ReentrantLock();

    @Override
    public void lock() {
        mutex.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        mutex.lockInterruptibly();
    }

    @Override
    public boolean tryLock() {
        return mutex.tryLock();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return mutex.tryLock(time, unit);
    }

    @Override
    public void unlock() {
        mutex.unlock();
    }

    @Override
    public Condition newCondition() {
        return mutex.newCondition();
    }
}
