package threadCreate;

/**使用继承Thread的方式实现多线程
 * 三个窗口卖100张票
 * 当前线程不安全，出现卖重票问题
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        Window w1 = new Window("窗口1");
        Window w2 = new Window("窗口2");
        Window w3 = new Window("窗口3");
        w1.start();
        w2.start();
        w3.start();
    }
}

class Window extends Thread{
    private static int ticket = 100;

    public Window(String name) {
        super(name);
    }

    @Override
    public void run() {
        while(ticket>0){
            System.out.println(getName()+"卖出了第："+ ticket +"号票");
            ticket--;
        }
    }
}

