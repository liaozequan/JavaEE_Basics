package async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**FutureTask是实现异步多线程的一个类，当存在缺陷
 * FutureTask缺陷：
 * get()容易造成阻塞,一般建议放在程序后面
 * FutureTask的优化---->使用CompletableFuture
 */
public class FutureTaskWeakness {
    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<String>(()->{
            System.out.println(Thread.currentThread().getName() + "--- come in");
            TimeUnit.SECONDS.sleep(5);
            return "task over";
        });
        new Thread(futureTask, "t1").start();
        //这里主线程调用了futureTask.get()，导致等待futureTask的返回值，而futureTask要等到5s后才返回
        //就导致主线程阻塞，无法做其他任务
        //System.out.println(futureTask.get());
        //只等待3s，不然报超时异常
        //System.out.println(futureTask.get(3, TimeUnit.SECONDS));
        /**
         * 轮询查看futureTask是否完成任务
         */
        while (true){
            if(futureTask.isDone()){
                System.out.println(futureTask.get());
                break;
            }else {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("正在处理中");
            }
        }
        //主线程做其他任务
        System.out.println(Thread.currentThread().getName() + "做其他任务");

    }
}
