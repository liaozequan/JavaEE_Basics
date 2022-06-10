package threadCreate;

/**使用实现runnable接口的方式实现多线程
 * 三个窗口卖100张票
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        Window2 w = new Window2();
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
class Window2 implements Runnable{
    private int ticket = 100;
    @Override
    public void run() {
        while(ticket>0){
            System.out.println(Thread.currentThread().getName()+"卖出了第："+ ticket +"号票");
            ticket--;
        }
    }
}
