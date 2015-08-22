package chapter03.Locks;

/**
 * Created by larry on 15-5-30.
 * 内部锁
 */
public class SynchronizedBenchmarkDemo implements Counter{
    private long count =0;

    public long getValue() {
        return count;
    }

    public synchronized void increment() {
        count++;
    }
}
