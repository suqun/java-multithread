package chapter01;

/**
 * Created by Administrator on 2015/4/11.
 */
public class MyThread1 extends Thread {

    public MyThread1(String name){
        super(name);//�����̵߳�����
    }

    public static void main(String[] args){
        for (int i=0; i<5;i++){
            new MyThread1("thread" + i).start();
        }
    }

    @Override
    public void run(){
        for (int i = 0; i < 20; i++) {
            System.out.println(this.getName() + ":" + i);
        }
    }
}
