package chapter03;

/**
 * Created by Administrator on 2015/4/15.
 * 使用原子量实现银行取款
 */
public class Account {
    private double balance;

    public Account(double balance){
        this.balance = balance;
        System.out.println("Totle Money: " + balance);
    }

    public void deposit (double money) {
        balance = balance + money;
    }

    public synchronized void withdraw (double money, int delay) {
        if (balance >= money){
            try {
                Thread.sleep(delay);
                balance = balance - money;
                System.out.println(Thread.currentThread().getName()
                        + " withdraw " + money + " successful!" +
                        balance);
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
