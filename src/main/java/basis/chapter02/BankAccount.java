package basis.chapter02;

/**
 * Created by Administrator on 2015/4/12.
 * 线程干扰
 */
public class BankAccount {
    private int number;
    private int balance;

    public BankAccount(int number,int balance){
        this.number = number;
        this.balance = balance;
    }

    public int getBalance(){
        return  balance;
    }

    //存款
    public  void deposit(int amount){//public sychronized void deposit***
        synchronized (this) {
            balance = balance + amount;
        }
    }

    //取款
    public  void withdraw(int amount){//public sychronized void withdraw***
        synchronized (this) {
            balance = balance - amount;
        }
    }

    public static void main(String[] args){
        try {
            BankAccount a = new BankAccount(1,1000);
            Thread t1 = new Thread(new Depositor(a,100),"depositor");
            Thread t2 = new Thread(new Withdraw(a,100),"withdraw");
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println(a.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static class Depositor implements Runnable{
        BankAccount account;
        int amount;
        public Depositor(BankAccount account, int amount){
            this.account = account;
            this.amount =  amount;
        }
        public void run(){
            for (int i = 0; i<100000; i++){
                account.deposit(amount);
            }
        }
    }

    static class Withdraw implements Runnable{
        BankAccount account;
        int amount;
        public Withdraw(BankAccount account, int amount){
            this.account = account;
            this.amount =  amount;
        }
        public void run(){
            for (int i = 0; i<100000; i++){
                account.withdraw(amount);
            }
        }
    }
}
