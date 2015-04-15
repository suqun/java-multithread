package chapter01;

/**
 * Created by Administrator on 2015/4/11.
 */
public class InterruptTest2 extends Thread {
    static int result = 0;
    public InterruptTest2(String name){
        super(name);
    }

    public static void main(String[] args){
        System.out.println("主线程开始执行");
        Thread t =  new InterruptTest2("计算线程");
        t.start();
        System.out.println("result:"+result);
        try {
            long start = System.nanoTime();
            t.join(3);
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
        for(int i=0; i<100000; i++){
            result ++;
            if(Thread.interrupted()){
                System.out.println(this.getName() + "--被中断，结束");
                //throw new InterruptedException();
                return;
            }
        }

        System.out.println(this.getName()+"--计算结束");
    }
}
