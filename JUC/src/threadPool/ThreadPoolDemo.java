package threadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池3中分类
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //newFixedThreadPool(num)：一池num个线程
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        //newSingleThreadExecutor():一池一线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        //newCachedThreadPool():一池线程可扩容
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        //5个线程处理10个请求
        try {
            for (int i = 1; i <= 100; i++) {
                final int num = i;
                threadPool3.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"::办理业务"+num);
                });
            }
        }finally {
            threadPool3.shutdown();
        }


    }
}
