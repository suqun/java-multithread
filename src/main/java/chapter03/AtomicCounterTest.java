package chapter03;

/**
 * Created by Administrator on 2015/4/15.
 * AtomicCounter测试类
 */
public class AtomicCounterTest extends Thread {
    AtomicCounter counter;

    public AtomicCounterTest(AtomicCounter counter){
        this.counter = counter;
    }

    @Override
    public void run(){
        int i = counter.increment();
        System.out.println("generated number:" + i);
    }

    public static void main(String[] args) {
        AtomicCounter counter = new AtomicCounter();
        for(int i=0; i<10;i++){
            new AtomicCounterTest(counter).start();
        }
    }
}
