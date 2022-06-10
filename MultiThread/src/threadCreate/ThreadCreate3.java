package threadCreate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建多线程方法3：实现callable接口
 * 步骤1-6
 * 如何理解 实现callable接口 比 实现runnable接口 创建多线程更强大？
 * 1.call()可以有返回值，run()不能有返回值
 * 2.call()可以抛出异常，
 * 3.callable支持泛型
 */

public class ThreadCreate3 {
    public static void main(String[] args) {
        //3.实例化实现了callable接口类的对象
        MyThread1 thread1= new MyThread1();
        //4.将callable接口类的对象作为参数放入构造器实例化FutureTask对象
        FutureTask futureTask = new FutureTask(thread1);
        //5.将FutureTask为参数放入构造器实例化Thread对象
        Thread thread = new Thread(futureTask);
        //6.开启线程
        thread.start();
        try {
            //futureTask.get()值为：FutureTask构造器参数callable实现类重写的call()的返回值
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

//1.实现callable接口
class MyThread1 implements Callable{

    //2.重写call()方法，并将线程操作声明到方法中
    @Override
    public Object call() throws Exception {
        int i;
        for(i=0; i<100; i++){
            System.out.println(Thread.currentThread().getName()+" : "+i);
        }
        return i;
    }
}
