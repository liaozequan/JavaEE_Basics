package async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 三个任务使用异步多线程完成
 */
public class FutureThreadPoolDemo {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        FutureTask<String> futureTask1 = new FutureTask<String>(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return "task1 over";
        });
        //将第一个异步任务交给线程池
        threadPool.submit(futureTask1);

        FutureTask<String> futureTask2 = new FutureTask<String>(()->{
            TimeUnit.MILLISECONDS.sleep(300);
            return "task2 over";
        });
        //将第2个异步任务交给线程池
        threadPool.submit(futureTask2);
        threadPool.shutdown();
        //将第3个任务交给主线程
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时："+ (endTime-startTime));
    }
}
