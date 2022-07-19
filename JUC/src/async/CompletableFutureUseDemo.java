package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**CompletableFuture替代future <FutureTaskWeakness.java>
 *
 */
public class CompletableFutureUseDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + "--- come in");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int result = 1024;
            System.out.println("----5s后出结果:" + result);
            return result;
        }, threadPool).whenComplete((v, e)->{
            if(e == null){//e是异常信息
                //如果无异常
                System.out.println("任务完成，返回值" + v);
            }
        }).exceptionally(e->{
            e.printStackTrace();
            System.out.println("异常情况" + e.getCause() + "\t" + e.getMessage());
            return null;
        });
        //主线程做其他任务
        System.out.println(Thread.currentThread().getName() + "做其他任务");
        //关闭线程池
        threadPool.shutdown();
        //CompletableFuture如果不指定线程池的话
        // 执行异步线程是一个守护线程，为防止主线程结束时，守护线程会强制停止，主线程多等待一会
        //当然最好自己指定线程池，就不会被当做守护线程了
//        try {
//            TimeUnit.SECONDS.sleep(7);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
