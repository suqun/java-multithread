package basis.chapter03.Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by larry on 15-5-30.
 * 不公平重入锁
 */
public class ReentrantLockUnfairBeanchmarkDemo implements Counter {
    private volatile long count = 0;
    private Lock lock;

    public ReentrantLockUnfairBeanchmarkDemo(){
        //使用非公平锁，true就是公平锁
        lock = new ReentrantLock(false);
    }

    public long getValue() {
        return count;
    }

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
}
