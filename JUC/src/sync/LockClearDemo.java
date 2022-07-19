package sync;

/**
 * 锁消除案例
 */
public class LockClearDemo {
    public static Object objectLock = new Object();

    /**
     * 每个线程执行m()方法，都会实例化一个Object锁，则每个线程各持有自己的锁
     * 即锁不是共享的，这个同步代码块无意义
     */
    public void m(){
        Object o = new Object();
        synchronized (o){
            System.out.println(Thread.currentThread().getName()+"::" + o.hashCode() +"==="+ objectLock.hashCode());
        }
    }

    public static void main(String[] args) {
        LockClearDemo lockClearDemo = new LockClearDemo();
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                lockClearDemo.m();
            }, String.valueOf(i)).start();
        }
    }
}
