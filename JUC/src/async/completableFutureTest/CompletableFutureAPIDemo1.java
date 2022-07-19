package async.completableFutureTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture API demo
 * 获得结果和打断任务
 */
public class CompletableFutureAPIDemo1 {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });
//        System.out.println(completableFuture.get());
//        System.out.println(completableFuture.get(2, TimeUnit.SECONDS));
        //不需要抛异常
//        System.out.println(completableFuture.join());
        //getNow("xxx")如果计算完成返回结果，否则立即返回 xxx
        System.out.println(completableFuture.getNow("xxx"));
        //complete("finish") 如果线程完成任务，则返回false（表示未打断线程），后续的get()/join()会返回线程返回的值
        //如果线程未完成任务，就执行了complete(),则返回true（表示打断线程），后续的get()/join()会返回complete()中的参数
        System.out.println(completableFuture.complete("finish") + "\t" + completableFuture.join());
    }
}
