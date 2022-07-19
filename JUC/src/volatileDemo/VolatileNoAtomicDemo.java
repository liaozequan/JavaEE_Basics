package volatileDemo;

import java.util.concurrent.TimeUnit;

/**
 * volatile变量非原子性测试
 * 此案例中有无volatile修饰都是一样的
 * 因此volatile修饰的变量不适应于依赖当前值的变量（如i=i+1）
 * 适用于作为某种状态的值（如boolean，int）
 */

/**非原子性具体过程：
 * 1.t1线程将主存中的共享数据放入到自己线程的本地内存中（如num=1）
 * 同时t2线程将主存中的共享数据放入到自己线程的本地内存中（如num=1）
 * 2.t1先进行计算将num++ --> num=2，保存到自己的本地内存中
 * 同时t2正在进行计算将num++ --> num=2
 * 3.此时t1先将本地内存中的值写会到主存，根据volatile的可见性，会立即通知其他线程主存里的num被改为最新值
 * 4.此时的t2线程收到通知，立即将本地内存中的num变量作废，重新从主存中读取num最新值2
 * 这一系列过程后，num=2，而t1，t2线程都给num+1，出现了与预期结果不一致问题
 */
class MyNumber{
    volatile int num;
    //改进可以加synchronized
    public void incrNum(){
        num++;
    }
}
public class VolatileNoAtomicDemo {
    public static void main(String[] args) throws InterruptedException {
        MyNumber myNumber = new MyNumber();
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myNumber.incrNum();
                }
            }, String.valueOf(i)).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName() + "::num=" + myNumber.num);
    }
}
