package chapter01;

/**
 * Created by Administrator on 2015/4/11.
 */
public class JoinTest extends Thread {
    static int result = 0;
    public JoinTest(String name){
        super(name);
    }

    public static void main(String[] args){
        System.out.println("主线程开始执行");
        Thread t =  new JoinTest("计算线程");
        t.start();
        System.out.println("result:"+result);
        long start = System.nanoTime();
        try {
            t.join();
            //t.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();

        System.out.println((end-start)/1000000 + "毫秒后，result=" + result);
    }

    @Override
    public void run(){
        System.out.println(this.getName()+"--开始计算");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = (int)(Math.random()*1000);
        System.out.println(this.getName()+"--计算结束");
    }
}
