package basis.chapter03;

/**
 * Created by Administrator on 2015/4/15.
 * 测试帐户类
 */
public class AccountThread extends Thread{
    Account account;
    int delay;

    public AccountThread (Account account, int delay){
        this.account = account;
        this.delay =   delay;
    }

    public void run () {
        account.withdraw(100,delay);
    }

    public static void main(String[] args) {
        Account account = new Account(100);
        AccountThread accountThread1 = new AccountThread(account,1000);
        AccountThread accountThread2 = new AccountThread(account,0);
        accountThread1.start();
        accountThread2.start();
    }
}
