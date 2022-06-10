package threadSafety;

/**解决线程安全问题2
 * 例：多窗口售票，实现runnable类的方式来创建多线程。
 * 解决方法：当一个线程a在操作ticket时，其他线程不能参与进来。直到线程a处理完ticket，其他线程才能开始处理ticket
 *          即使线程a阻塞，ticket也不能被其他线程操作
 *
 * 在java中，通过同步机制，解决线程安全问题
 * 方法一，同步代码块
 *      synchronized(同步监视器){
 *          //需要被同步的代码
 *      }
 *      同步监视器:锁，任何类的对象都可以当锁，要求多个线程必须公用同一把锁
 *
 * 方法二，同步方法
 *
 */
public class ThreadSafeTest2 {
    public static void main(String[] args) {
        Window w = new Window();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }

}
class Window implements Runnable{
    private int ticket = 100;
    Object object = new Object();
    @Override
    public void run() {
        synchronized(object){   //object最好替换为this
            while(ticket>0){
                System.out.println(Thread.currentThread().getName()+"卖出了第："+ ticket +"号票");
                ticket--;
            }
        }

    }
}

