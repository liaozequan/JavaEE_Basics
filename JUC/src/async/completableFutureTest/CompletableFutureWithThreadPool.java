package async.completableFutureTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture异步任务对线程池的选择
 * 1.不指定线程池，则都使用ForkJoinPool
 * 2.不指定线程池时，如果异步线程处理够快，下面的thenRun任务会使用main线程处理
 * 3.指定了线程池，异步任务和thenRun任务都使用指定线程池
 * 4.指定了线程池，异步任务使用指定线程池，而thenRunAsync任务使用ForkJoinPool,即使后面的是thenRun任务也使用ForkJoinPool
 */
public class CompletableFutureWithThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1号任务" + Thread.currentThread().getName());
            return "1111";
        },threadPool).thenRunAsync(()->{
            System.out.println("2号任务" + Thread.currentThread().getName());
        }).thenRun(()->{
            System.out.println("3号任务" + Thread.currentThread().getName());
        }).thenRun(()->{
            System.out.println("4号任务" + Thread.currentThread().getName());
        });
        TimeUnit.SECONDS.sleep(2);
        threadPool.shutdown();
    }
}
