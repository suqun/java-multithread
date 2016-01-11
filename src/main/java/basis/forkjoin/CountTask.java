package basis.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by larry on 15-7-3.
 * Fork/Join
 */
public class CountTask extends RecursiveTask{
    private static final int THRESHOLD = 2;//阀值
    private int start;
    private int end;

    public CountTask(int start, int end){
        this.start = start;
        this.end = end;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Object compute() {
        int sum = 0;
        boolean canCompute = (end-start) <= THRESHOLD;
        //如果任务足够小就计算任务
        if(canCompute){
            for(int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            //如果任务大于阀值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start,middle);
            CountTask rightTask = new CountTask(middle + 1,end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            //等待子任务完成，并得到结果
            int leftResult = (Integer)leftTask.join();
            int rightResult = (Integer)rightTask.join();
            //合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void countByFJ (int start, int end){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //生成一个计算任务，负责计算1+2+3+4
        CountTask task = new CountTask(start,end);
        //执行一个任务
        Future result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //isCompletedAbnormally()方法来检查任务是否已经抛出异常或已经被取消
        if (task.isCompletedAbnormally()) {
            //getException方法返回Throwable对象，如果任务被取消了则返回CancellationException。
            //如果任务没有完成或者没有抛出异常则返回null。
            System.out.println(task.getException());
        }
    }

    public static void main(String[] args){
        countByFJ(1, 100000000);
    }
}
