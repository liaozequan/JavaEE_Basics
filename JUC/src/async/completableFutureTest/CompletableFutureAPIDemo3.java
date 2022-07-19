package async.completableFutureTest;

import java.util.concurrent.CompletableFuture;

/**CompletableFuture API demo
 *thenAccept
 * thenRun
 * thenApply
 */
public class CompletableFutureAPIDemo3 {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(()->{
            return 1;
        }).thenApply(f->{
            return f+2;
        }).thenApply(f->{
            return f+3;
        //thenAccept得到任务最后结果
        }).thenAccept(System.out :: println).join();

        CompletableFuture.supplyAsync(()->{
            return 1;
        //thenRun 任务完成后执行，不需要得到任务结果，也不需要返回值。和上一步任务没关系
        }).thenRun(()->{
            System.out.println("finish");
        }).join();

        CompletableFuture.supplyAsync(()->{
            return 1;
            //thenApply 得到任务最后结果,需要有返回值。
        }).thenApply(t->{
            System.out.println("finish");
            return t;
        }).join();
    }
}
