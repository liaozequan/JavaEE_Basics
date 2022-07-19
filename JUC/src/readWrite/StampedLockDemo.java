package readWrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * 邮戳锁
 */
public class StampedLockDemo {
    static int number = 10;
    static StampedLock stampedLock = new StampedLock();

    public void write(){
        //获得写锁戳记
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + "::写线程获取写锁成功，准备写入");
        try {
            number = number + 5;
        }finally {
            //对比戳记来释放锁
            stampedLock.unlockWrite(stamp);
        }
        System.out.println(Thread.currentThread().getName() + "::写线程修改完成");
    }

    public void read(){
        //获得写锁戳记
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + "::读线程获取读锁成功，开始读");
        //模拟读取数据4秒
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            int result = number;
            System.out.println(Thread.currentThread().getName() + "::读取数据为:" + result);
        }finally {
            //对比戳记来释放锁
            System.out.println(Thread.currentThread().getName() + "::读线程成功释放读锁");
            stampedLock.unlockRead(stamp);
        }
    }

    /**
     * 乐观读:读没有完成时，允许写
     * 在线程持有乐观读锁时，有其他线程抢占写锁，允许该写线程持有写锁，并写入数据
     * 持有乐观读锁的读线程在读取数据时，需要调用validate(stamp)，检查返回值
     *      如果返回true：说明在获取乐观读锁到读取数据的过程中，没有写线程介入（即数据没有被修改）
     *      如果返回false：说明在获取乐观读锁到读取数据的过程中，有写线程介入（即数据被修改）
     *      ，此时需要将乐观读锁改为悲观读锁，重新读取数据
     */
    public void optimisticRead(){
        long stamp = stampedLock.tryOptimisticRead();
        System.out.println(Thread.currentThread().getName() + "::获取乐观读锁成功");
        int result = number;
        System.out.println("4s前stampedLock.validate(stamp)的返回值:true无修改，false被修改::" + stampedLock.validate(stamp));
        //模拟读取数据4秒
        for (int i = 1; i <= 4; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "::" + i + "秒后"
            + "stampedLock.validate(stamp)的返回值:true无修改，false被修改::" + stampedLock.validate(stamp));
            if (!stampedLock.validate(stamp)){
                System.out.println("有写操作");
                //如果在读过程中，有写操作，这将乐观读锁改为悲观读锁
                stamp = stampedLock.readLock();
                try {
                    System.out.println("乐观读--->悲观读");
                    result = number;
                    System.out.println("重新悲观读后得到数据::" + result);
                }finally {
                    stampedLock.unlockRead(stamp);
                }
            }
            System.out.println(Thread.currentThread().getName() + "::result::" + result);
        }

    }

    public static void main(String[] args) {
        StampedLockDemo resource = new StampedLockDemo();
        new Thread(()->{
            resource.optimisticRead();
        },"readThread").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("写线程开始，尝试获取写锁");
            resource.write();
        },"writeThread").start();
    }
}
