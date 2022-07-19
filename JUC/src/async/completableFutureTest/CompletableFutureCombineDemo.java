package async.completableFutureTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/** thenCombine 多个异步任务结果合并
 * 如果一个异步任务先完成，这要等待另一个任务完成，才能合并结果
 */
public class CompletableFutureCombineDemo {
    public static void main(String[] args) {
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "::启动");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "::启动");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 15;
        });
        CompletableFuture<Integer> result = task1.thenCombine(task2, (x, y) -> {
            //x,y是两个task的返回值
            System.out.println("开始结果合并");
            return x + y;
        });
        System.out.println(result.join());
    }
}
