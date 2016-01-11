package aojcp.chapter01;

/**
 * Created on 16/1/11
 * 1.1.1 多线程一定快吗?
 *
 */
public class ConcurrentTest {
    public static final long count = 100000000l;

    public static void main(String[] args) throws InterruptedException {
        concrrency();
        serial();
    }

    public static void concrrency() throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread thread = new Thread(new Runnable() {
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += i;
                }
            }
        });

        thread.start();

        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();

        System.out.println("concrrency : " + time + "ms,b=" + b);

    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += i;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }

        long time = System.currentTimeMillis() - start;

        System.out.println("serial : " + time + "ms,b=" + b);
    }
}

/*
    1. 循环次数1万,并发的慢
        concrrency : 1ms,b=-10000
        serial : 0ms,b=-10000
    2. 循环次数10万,并发的慢
        concrrency : 3ms,b=-100000
        serial : 2ms,b=-100000
    3. 循环次数100万,差不多
        concrrency : 6ms,b=-1000000
        serial : 6ms,b=-1000000
    4. 循环次数1000万,并发的快
        concrrency : 7ms,b=-10000000
        serial : 11ms,b=-10000000
    5. 循环次数10000万,并发的约快1倍
        concrrency : 40ms,b=-100000000
        serial : 75ms,b=-100000000
 */