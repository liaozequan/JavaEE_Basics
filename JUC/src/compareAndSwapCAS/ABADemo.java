package compareAndSwapCAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**AtomicStampedReference解决ABA问题
 *
 */
public class ABADemo {
    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference<Integer> stampedReference= new AtomicStampedReference<>(100,1);
    public static void main(String[] args) {
        new Thread(()->{
            //获取版本号
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + ":当前版本号:" + stamp);
            //暂停500ms，保证t2线程获取的版本号与t1一致
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stampedReference.compareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + ":当前版本号:" + stampedReference.getStamp());

            stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + ":当前版本号:" + stampedReference.getStamp());

        }, "t1").start();

        new Thread(()->{
            //获取版本号
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + ":当前版本号:" + stamp);
            //等t1线程全部执行完成
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //t2线程使用获得的版本号去写回主存
            boolean b = stampedReference.compareAndSet(100, 999, stamp, stamp+1);
            //获得的版本号与主存的版本号不相等，写回失败
            System.out.println(b +"--"+ Thread.currentThread().getName() + ":当前版本号:" + stampedReference.getStamp());

        }, "t2").start();
    }

    /**ABA问题：
     * 当t1，t2线程同时将atomicInteger=100存入自己的本地内存中
     * t1先将atomicInteger修改为101，写回主存，又将atomicInteger修改为100，再写回主存
     * 而此时t2希望将atomicInteger修改为999写回主存，根据CAS要先判断自己的期望值是否和主存里的值一致
     * 发现都是100，则atomicInteger=999成功写回主存
     *
     * 这一过程看似没有问题，但是实际上t1已经修改主存了2次了，而t2认为主存并没有修改过
     */
    public static void ABA(){
        new Thread(()->{
            atomicInteger.compareAndSet(100, 101);
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicInteger.compareAndSet(100, 999) + "--" + atomicInteger.get());
        }, "t1").start();
    }
}
