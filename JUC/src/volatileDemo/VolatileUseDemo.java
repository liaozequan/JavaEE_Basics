package volatileDemo;

/**
 * 当读操作多于写操作时
 * 可以为读和写操作都加锁，但读操作加锁就每次只有一个线程读，效率非常低
 * 可以让读操作不加锁，让变量为volatile，保证其可见性
 * 让写操作加锁，保证其原子性
 */
public class VolatileUseDemo {
    private volatile int value;

    public int read(){
        return value;
    }

    public synchronized int write(){
        return value++;
    }
}
