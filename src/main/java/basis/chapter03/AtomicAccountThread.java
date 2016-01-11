package basis.chapter03;

/**
 * Created by Administrator on 2015/4/15.
 * 测试帐户类
 */
public class AtomicAccountThread extends Thread{
    AtomicAccount account;
    int delay;

    public AtomicAccountThread(AtomicAccount account, int delay){
        this.account = account;
        this.delay =   delay;
    }

    public void run () {
        account.withdraw(100,delay);
    }

    public static void main(String[] args) {
        AtomicAccount account = new AtomicAccount(100);
        AtomicAccountThread accountThread1 = new AtomicAccountThread(account,1000);
        AtomicAccountThread accountThread2 = new AtomicAccountThread(account,0);
        accountThread1.start();
        accountThread2.start();
    }
}
