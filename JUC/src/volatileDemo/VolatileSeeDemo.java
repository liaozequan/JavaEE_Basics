package volatileDemo;

import java.util.concurrent.TimeUnit;

/**
 * volatile可见性测试
 */
public class VolatileSeeDemo {
    static boolean flag = true;
    static volatile boolean condition = true;

    public static void main(String[] args) throws InterruptedException {
        /**flag是普通boolean
         * 当main线程修改了flag，此时仅仅修改了main线程中本地内存的flag值
         * 并没有立刻修改主存中共享变量flag的值，t1线程不会立刻得到flag的最新值
         * 即不具有可见性
         */
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "::come in");
            while (flag){

            }
            System.out.println(Thread.currentThread().getName() + "::flag被修改为false，线程终止");
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        flag = false;
        System.out.println(Thread.currentThread().getName() + "::将flag修改为:" + flag);

        TimeUnit.SECONDS.sleep(1);

        /**condition是volatile变量
         * t2线程每次读取condition都要从主存中取出最新值
         * 而main线程一修改condition值，就立即写会主存
         * 因此main一修改condition为false（本地内存中），立即写入到主存中
         * t2线程就立刻从主存中得到condition的最新值为false，结束循环
         * 具有可见性
         */
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "::come in");
            while (condition){

            }
            System.out.println(Thread.currentThread().getName() + "::condition被修改为false，线程终止");
        }, "t2").start();
        TimeUnit.SECONDS.sleep(1);
        condition = false;
        System.out.println(Thread.currentThread().getName() + "::将condition修改为:" + condition);
    }


}
