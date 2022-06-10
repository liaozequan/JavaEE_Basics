package threadCreate;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建多线程方法3：线程池
 */
public class ThreadCreate4 {
    public static void main(String[] args) {
        //1.提供指定线程个数的线程池
        ExecutorService es = Executors.newFixedThreadPool(10);
        //线程池配置
        ThreadPoolExecutor service = (ThreadPoolExecutor) es;
        //核心池大小
        service.setCorePoolSize(10);
        //最大线程数
        service.setMaximumPoolSize(10);

        //2.执行指定线程操作，需要提供实现callable或runnable接口的实现类对象
        //es.submit();//适用于callable
        es.execute(new MyThread2());//适用于runnable

        //3.关闭线程池
        es.shutdown();
    }
}

class MyThread2 implements Runnable{

    @Override
    public void run() {
        for(int i=0; i<100; i++){
            System.out.println(Thread.currentThread().getName()+" : "+i);
        }
    }
}
