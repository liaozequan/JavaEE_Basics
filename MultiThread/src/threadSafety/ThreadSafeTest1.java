package threadSafety;
/**解决线程安全问题1
 * 使用继承Thread的方式实现多线程
 * 三个窗口卖100张票，当前线程不安全，出现卖重票问题
 * 在java中，通过同步机制，解决线程安全问题
 * 方法一，同步代码块
 *      synchronized(同步监视器){
 *          //需要被同步的代码
 *      }
 *      同步监视器:锁，任何类的对象都可以当锁，要求多个线程必须公用同一把锁
 *
 * 方法二，同步方法
 */
public class ThreadSafeTest1 {
    public static void main(String[] args) {
        Window1 w1 = new Window1("窗口1");
        Window1 w2 = new Window1("窗口2");
        Window1 w3 = new Window1("窗口3");
        w1.start();
        w2.start();
        w3.start();
    }
}



class Window1 extends Thread{
    private static int ticket = 100;
    private static Object object = new Object();

    public Window1(String name) {
        super(name);
    }

    @Override
    public void run() {
        //正确方式：
        //synchronized(object){
        synchronized(Window1.class){    //Window1.class 类也是对象，且类只加载一次
        //错误方式，this表示t1,t2,t3
        //synchronized(this){
            while(ticket>0){
                System.out.println(getName()+"卖出了第："+ ticket +"号票");
                ticket--;
            }
        }

    }
}