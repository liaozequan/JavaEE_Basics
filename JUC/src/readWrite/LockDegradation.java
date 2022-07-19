package readWrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁降级（写锁-->读锁）
 * 先获取写锁--再获取读锁--释放锁
 * 即边写边读
 * 而读锁不能升级为写锁：因为读过程不能写
 */
public class LockDegradation {
    public static void main(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();//读锁
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();//写锁
        //锁降级
        //1.获取写锁
        writeLock.lock();
        System.out.println("写操作");
        //2.获取读锁-->将写锁降级为读锁
        readLock.lock();
        //写操作过程中，也可以读
        System.out.println("读操作");
        //3.释放写锁
        writeLock.unlock();
        //4.释放读锁
        readLock.unlock();

        /**
         * 读锁不能升级为写锁
         */
        readLock.lock();
        //写操作
        System.out.println("读操作");
        //运行到这里就阻塞了，因为读的过程不能写
        writeLock.lock();
        System.out.println("写操作");
    }
}
