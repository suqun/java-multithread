package basis.chapter03;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2015/4/15.
 * 使用原子量实现银行取款
 */
public class AtomicAccount {
    private AtomicLong balance;

    public AtomicAccount(long balance){
        this.balance = new AtomicLong(balance);
        System.out.println("Totle Money: " + balance);
    }

    public void deposit (long money) {
        balance.addAndGet(money);
    }

    public void withdraw (long money, int delay) {
        long oldValue = balance.get();
        if (oldValue >= money){
            try {
                Thread.sleep(delay);
                if (balance.compareAndSet(oldValue,oldValue-money)){
                    System.out.println(Thread.currentThread().getName()
                            + " withdraw " + money + " successful!" +
                            balance);
                }else{
                    System.out.println(Thread.currentThread().getName()
                            + "thread concurrent, withdraw failed!" +
                            balance);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println(Thread.currentThread().getName()
                    + " balance is not enough, withdraw failed!" +
                    balance);
        }
    }
}
