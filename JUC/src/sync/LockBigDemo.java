package sync;

/**
 * 锁粗化案例：
 * 如果多个同步代码块相邻，且都是同一个锁对象，这JIT编译器会将这几个同步代码块合并为一个大的块
 * 一次申请锁即可，避免了多次申请释放锁
 */
public class LockBigDemo {
    static Object objectLock = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (objectLock){
                System.out.println("11111111");
            }
            synchronized (objectLock){
                System.out.println("22222222");
            }
            synchronized (objectLock){
                System.out.println("33333333333");
            }
        }).start();
    }
}
