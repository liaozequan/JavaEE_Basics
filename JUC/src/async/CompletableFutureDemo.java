package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步回调
 * CompletableFuture 的3种使用
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //不建议使用这种方法实例化
        //CompletableFuture completableFuture = new CompletableFuture();
        //创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //异步调用，无返回值,不指定线程池
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() + "completableFuture1");
        });
        completableFuture1.get();
        //异步调用，无返回值,指定线程池
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() + "completableFuture2");
        }, threadPool);
        completableFuture2.get();
        //异步调用，有返回值,指定线程池
        CompletableFuture<Integer> completableFuture3 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + "completableFuture2");
            return 1024;
        }, threadPool);
        System.out.println(completableFuture3.get());

        completableFuture3.whenComplete((t, u)->{
            System.out.println("t---:" + t);//返回值
            System.out.println("u----:" + u);//u是异常信息
        }).get();
        //关闭线程池
        threadPool.shutdown();
    }
}
