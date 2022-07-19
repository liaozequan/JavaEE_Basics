package sync;

/**
 * 可重入锁实例：synchronized实现(隐式)
 * 当一个线程获得锁时，底层会有一个计数器recursions+1，当这个线程释放一个锁时，recursions-1
 * 当recursions=0时才完全释放了锁
 */
public class SyncLockDemo {
    public static void main(String[] args) {
        Object o = new Object();
        new Thread(()->{
            synchronized (o){
                System.out.println(Thread.currentThread().getName() + "外层");
                synchronized (o){
                    System.out.println(Thread.currentThread().getName() + "中层");
                    synchronized (o){
                        System.out.println(Thread.currentThread().getName() + "内层");
                    }
                }
            }
        },"t1").start();
    }
}
