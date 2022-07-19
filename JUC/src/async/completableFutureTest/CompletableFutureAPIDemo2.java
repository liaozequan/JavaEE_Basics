package async.completableFutureTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture API demo
 *thenApply
 * handle
 */
public class CompletableFutureAPIDemo2 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        /**
         * thenApply 在一个异步线程中串行执行多个步骤，但是某一步出异常就在某一步停止
         */
//        CompletableFuture.supplyAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("第一步");
//            return "起锅";
//        }, threadPool).thenApply(f->{
//            System.out.println("第二步");
//            //f是上一步的返回值
//            return f + "->烧油";
//        }).thenApply(f->{
//            System.out.println("第三步");
//            return f + "->炒菜";
//        }).whenComplete((v, e)->{
//            //完成所有任务后，返回总结果
//            if(e == null){
//                System.out.println("过程：" + v);
//            }
//        }).exceptionally(e->{
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//            return null;
//        });
        /**handle(f,e) f是上一步返回值，e是上一步异常
         * 在一个异步线程中串行执行多个步骤，某一步出异常能继续处理任务
         */
        CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一步");
            return "起锅";
        }, threadPool).handle((f,e)->{
            System.out.println("第二步");
            int i = 1/0;
            //f是上一步的返回值
            return f + "->烧油";
        }).handle((f,e)->{
            System.out.println("第三步");
            return f + "->炒菜";
        }).whenComplete((v, e)->{
            //完成所有任务后，返回总结果
            if(e == null){
                System.out.println("过程：" + v);
            }
        }).exceptionally(e->{
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });
        threadPool.shutdown();
    }
}
