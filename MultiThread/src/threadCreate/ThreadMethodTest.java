package threadCreate;

/** thread类的常用方法
 *  1.run()
 *  2.start()
 *  3.currentThread() 返回执行当前代码的线程对象
 *  4.getName()\setName() 取得\设置 线程对象的名字
 *  5.通过构造器给线程命名
 *  6.yield() 释放当前cpu执行权，随后cpu可能被其他线程占用，也可能还是被当前线程抢到
 *  7.join() 在 线程A 中调用 线程B的join()，此时线程A阻塞，直到线程B执行完，才结束阻塞，待分配资源
 *  8.sleep() 主动阻塞线程
 *  9.isAlive() 判断线程是否存活
 *
 *  线程优先级：
 *  1.MAX_PRIORITY = 10 | NORM_PRIORITY = 5 | MIN_PRIORITY = 1
 *  2.查询，设置线程优先级
 *      getPriority() \\ setPriority()
 *  3.高优先级的线程会强占低优先级线程，但不意味着只有当高优先级线程执行完才会执行低优先级
 */
public class ThreadMethodTest {
    public static void main(String[] args) {
        TestThread testThread = new TestThread("线程一");
        testThread.setName("线程一");
        testThread.setPriority(Thread.MAX_PRIORITY);
        testThread.start();

        //给主线程命名
        Thread.currentThread().setName("主线程");
        for(int i=100; i<120; i++){
            System.out.println(Thread.currentThread().getName()+" : "+i);
            if(i==110){
                try {
                    testThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


class  TestThread extends Thread{

    //2.重写thread类的run()方法
    @Override
    public void run() {
        for(int i=0; i<20; i++){
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(Thread.currentThread().getName()+" : "+i);
            if(i%5 == 0){
                yield();
            }
        }
    }

    public TestThread(String name) {
        super(name);
    }
}