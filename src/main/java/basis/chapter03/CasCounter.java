package basis.chapter03;

/**
 * Created by Administrator on 2015/4/13.
 * 重新编写的计数器类来使用 CAS 替代锁定
 */
public class CasCounter {
    private SimulatedCAS value ;
    int delay;

    public CasCounter(SimulatedCAS value, int delay){
        this.value = value;
        this.delay = delay;
    }

    public int getValue(){
        return value.getValue();
    }

    public int getDelay(){
        return delay;
    }

    public int increment() {
        int oldValue = value.getValue();
        return value.compareAndSwap(oldValue, oldValue + 1);
    }

    public static void main (String[] args){
        SimulatedCAS value = new SimulatedCAS();
//        value.setValue(10);
        CasCounter counter = new CasCounter(value,(int)(Math.random()*2000));
        for (int i=0; i<10; i++){
            new Thread(new Runner(counter),"thread"+i).start();
        }
    }
}
