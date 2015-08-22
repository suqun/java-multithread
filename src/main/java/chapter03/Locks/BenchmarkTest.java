package chapter03.Locks;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by larry on 15-5-30.
 * 测试程序
 */
public class BenchmarkTest {
    private Counter counter;
    private CyclicBarrier cyclicBarrier;
    private int threadNum;
    private int loopNum;
    private String testName;

    public BenchmarkTest(Counter counter,int threadNum,int loopNum,
                         String testName){
        this.counter = counter;
        this.threadNum = threadNum;
        this.loopNum = loopNum;
        this.testName = testName;
    }

    public static void main(String[] args){
        try {
            int threadNum = 5000;
            int loopNum = 100;
            new BenchmarkTest(new SynchronizedBenchmarkDemo(),threadNum,
                    loopNum,"内部锁").test();
//            new BenchmarkTest(new ReentrantLockUnfairBeanchmarkDemo(),threadNum,
//                    loopNum,"不公平重入锁").test();
//            new BenchmarkTest(new ReentrantLockFairBeanchmarkDemo(),threadNum,
//                    loopNum,"公平重入锁").test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test() throws Exception{
        try {
            long start = System.currentTimeMillis();
            for(int i = 0; i < threadNum; i++){
                new TestThread(counter,loopNum).start();
            }
            // 等待所有任务线程创建,然后通过关卡, 统一执行所有线程
//            cyclicBarrier.await();
            // 等待所有任务计算完成
//            cyclicBarrier.await();
            long end = System.currentTimeMillis();
            System. out .println(this.testName + " count value:"
                    + counter.getValue());
            System.out.println(this.testName + " 花费时间:" + (end -
                    start) + "毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class TestThread extends Thread{
        int loopNum = 100;
        private Counter counter;
        public TestThread (Counter counter,int loopNum){
            this.loopNum = loopNum;
            this.counter = counter;
        }

        public void run(){
            try {
//                cyclicBarrier.await();//等待所有的线程开始
                for (int i=0; i<this.loopNum; i++){
                    counter.increment();
                }
//                cyclicBarrier.await();//等待所有的线程开始
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
