package chapter03;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2015/4/15.
 * 使用原子变量后的计数器
 */
public class AtomicCounter {
    private AtomicInteger value = new AtomicInteger();

    public int getValue(){
        return value.get();
    }

    public int increment(){
        return value.incrementAndGet();
    }

    public int increment(int i){
        return value.addAndGet(i);
    }

    public int decrement(){
        return value.decrementAndGet();
    }

    public int decrement(int i){
        return value.addAndGet(-i);
    }
}
