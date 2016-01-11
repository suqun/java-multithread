package basis.chapter01;

/**
 * Created by Administrator on 2015/4/11.
 *
 */
public class SleepTest{

    public static void main (String[] args){
        String[] arg = {"one","two","three","four"};
        long start = System.nanoTime();
        for (int i=0; i<arg.length;i++){
            try {
                System.out.println(arg[i]);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.nanoTime();
        System.out.println("总的时间" + (end-start)/1000000);
    }
}
