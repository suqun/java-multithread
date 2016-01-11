package basis.chapter03;

/**
 * Created by Administrator on 2015/4/13.
 * CAS 的工作原理（为了便于说明，用同步语法表示）
 */
public class SimulatedCAS {

    private int value ;

    public synchronized int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        if (value == expectedValue) {
            value = newValue;
        }
        return value;
    }
}
