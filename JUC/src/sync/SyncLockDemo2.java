package sync;

import java.util.concurrent.locks.ReentrantLock;
/**
 * 可重入锁实例：lock实现(显式)
 */
public class SyncLockDemo2 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "外层");
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "中层");
            } finally {
                lock.unlock();
            }
        } finally {
            lock.unlock();
        }
    }
}
