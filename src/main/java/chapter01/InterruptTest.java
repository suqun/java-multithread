package chapter01;

/**
 * Created by Administrator on 2015/4/11.
 */
public class InterruptTest extends Thread {
    static int result = 0;
    public InterruptTest(String name){
        super(name);
    }

    public static void main(String[] args){
        System.out.println("主线程开始执行");
        Thread t =  new InterruptTest("计算线程");
        t.start();
        System.out.println("result:"+result);
        try {
            long start = System.nanoTime();
            t.join(2000);
            long end = System.nanoTime();
            t.interrupt();
            System.out.println((end - start) / 1000000 +
                    "毫秒后，result=" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run(){
        System.out.println(this.getName()+"--开始计算");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(this.getName() + "--被中断，结束");
            e.printStackTrace();
            return;
        }
        result = (int)(Math.random()*1000);
        System.out.println(this.getName()+"--计算结束");
    }
}
