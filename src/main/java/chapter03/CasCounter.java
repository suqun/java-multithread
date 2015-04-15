package chapter03;

/**
 * Created by Administrator on 2015/4/13.
 * 重新编写的计数器类来使用 CAS 替代锁定
 */
public class CasCounter {
    private SimulatedCAS value ;

    public CasCounter(SimulatedCAS value){
        this.value = value;
    }

    public int getValue(){
        return value.getValue();
    }

    public int increment() {
        int oldValue = value.getValue();
        if (value.compareAndSwap(oldValue, oldValue + 1) != oldValue){
            System.out.println("=====================");
            oldValue = value.getValue();
        }
        return oldValue + 1;
    }

    public static void main (String[] args){
        SimulatedCAS value = new SimulatedCAS();
        value.setValue(0);
        CasCounter counter = new CasCounter(value);
        for (int i=0; i<10; i++){
            new Thread(new Runner(counter),"thread"+i).start();
        }
    }
}
