package async.completableFutureTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/** applyToEither
 * 比较哪个异步线程更快完成
 */
public class CompletableFutureFastDemo {
    public static void main(String[] args) {
        //一号任务
        CompletableFuture<String> play1 = CompletableFuture.supplyAsync(()->{
            System.out.println("play1 start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "play1";
        });
        //2号任务
        CompletableFuture<String> play2 = CompletableFuture.supplyAsync(()->{
            System.out.println("play2 start");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "play2";
        });
        CompletableFuture<String> result = play1.applyToEither(play2, f->{
            return f + " is winner";
        });
        System.out.println(result.join());
    }
}
