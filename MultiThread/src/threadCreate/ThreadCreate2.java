package threadCreate;

/**
 * 创建多线程方法2：实现runnable接口
 * 1.创建一个实现了runnable接口的类
 * 2.重写runnable接口中的run方法
 * 3.实例化对象
 * 4.将此对象作为参数传入Thread类构造器中，创建Thread对象
 * 5.通过Thread类的对象调用start()
 */
public class ThreadCreate2 {
    public static void main(String[] args) {
        MThread m = new MThread();
        Thread t1 = new Thread(m);
        t1.start();
    }


}

class MThread implements Runnable{
    @Override
    public void run() {
        for(int i=0; i<100; i++){
            System.out.println(Thread.currentThread().getName()+" : "+i);
        }
    }
}
