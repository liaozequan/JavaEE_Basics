package threadSafety;
/**解决线程安全问题3
 * 使用同步方法解决 继承Thread类出现的线程安全问题
 * 方法二，同步方法
 *  如果出现线程安全问题的代码是封装在一个方法中，不妨将这个方法设置为同步的
 *
 */
public class ThreadSafeTest3 {
    public static void main(String[] args) {
        Window3 w1 = new Window3("窗口1");
        Window3 w2 = new Window3("窗口2");
        Window3 w3 = new Window3("窗口3");
        w1.start();
        w2.start();
        w3.start();
    }
}

class Window3 extends Thread{
    private static int ticket = 100;

    public Window3(String name) {
        super(name);
    }

    @Override
    public void run() {
        while(ticket>0){
            sale();
        }
    }

    //private synchronized void sale(){    //错误方法：同步监视器:this
    private static synchronized void sale(){    //正确方法：同步监视器:Window4.class
        if(ticket>0){
            System.out.println(Thread.currentThread().getName()+"卖出了第："+ ticket +"号票");
            ticket--;
        }

    }
}