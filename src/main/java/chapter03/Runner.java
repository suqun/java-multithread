package chapter03;

/**
 * Created by Administrator on 2015/4/11.
 * Runner
 */
public class Runner implements Runnable{
    private CasCounter counter;
    public Runner (CasCounter counter){
        this.counter = counter;
    }

    public void run(){
        try {
            Thread.sleep(counter.getDelay());
            int value = counter.increment();
            System.out.println("线程: "+ Thread.currentThread().getName()
                    + "计算后值为：" + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
