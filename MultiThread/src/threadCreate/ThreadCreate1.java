package threadCreate;

/**
 * 创建多线程方法一：继承thread类
 * 1.创建一个继承与thread类的子类
 * 2.重写thread类的run()方法--->将此线程要做的操作声明在run中
 * 3.实例化对象
 * 4.通过对象调用start()方法
 *      start()作用：（1）启动当前线程（2）调用当前线程run()方法
 *
 * 5.如果要创建多个线程，则需要实例化多个对象，而不能多次调用start()
 */
public class ThreadCreate1 {
    public static void main(String[] args) {
        //3.实例化对象
        MyThread myThread = new MyThread();
        MyThread myThread2 = new MyThread();
        //4.通过对象调用start()方法
        myThread.start();
        //myThread.start();    start()只能调用一次
        myThread2.start();

        //main线程
        for(int i=500; i<600; i++){
            System.out.println(Thread.currentThread().getName()+" : "+i);
        }
    }
}

//1.创建一个继承与thread类的子类
class  MyThread extends Thread{

    //2.重写thread类的run()方法
    @Override
    public void run() {
        for(int i=0; i<100; i++){
            System.out.println(Thread.currentThread().getName()+" : "+i);
        }
    }
}
