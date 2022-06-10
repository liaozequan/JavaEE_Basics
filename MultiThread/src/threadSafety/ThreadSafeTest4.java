package threadSafety;

/**解决线程安全问题4
 * 使用同步方法解决实现runnable接口出现的线程安全问题
 * 方法二，同步方法
 *  如果出现线程安全问题的代码是封装在一个方法中，不妨将这个方法设置为同步的
 *
 */
public class ThreadSafeTest4 {
    public static void main(String[] args) {
        Window4 w = new Window4();
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

class Window4 implements Runnable{
    private int ticket = 100;
    @Override
    public void run() {
        while(ticket>0){
            sale();
        }
    }
    private synchronized void sale(){    //同步监视器:this
        if(ticket>0){
            System.out.println(Thread.currentThread().getName()+"卖出了第："+ ticket +"号票");
            ticket--;
        }

    }
}
