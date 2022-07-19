package compareAndSwapCAS.atomics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**AtomicMarkableReference
 * 只要被修改过，mark=true，记录是否被修改过
 */
public class AtomicMarkableReferenceDemo {
    //初始值 100， 初始标识false
    static AtomicMarkableReference markableReference = new AtomicMarkableReference<>(100, false);

    public static void main(String[] args) {
        new Thread(()->{
            boolean mark = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + ":默认标识:" + mark);
            //等待1s，让t2线程也拿到默认标识
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //对比初始值和默认标识，将值改为999， 将标识改为true
            boolean b = markableReference.compareAndSet(100, 999, mark, !mark);
            System.out.println("t1是否操作成功：" + b);
        }, "t1").start();

        new Thread(()->{
            boolean mark = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + ":默认标识:" + mark);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = markableReference.compareAndSet(100, 888, mark, !mark);
            System.out.println("t2是否操作成功：" + b);
        }, "t2").start();
    }
}
