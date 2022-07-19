package threadCommunication;

/**
 * 虚假唤醒问题
 * 下列代码实现：A、C线程让num +1， B、D线程让num -1,使得num一直在 1\0浮动
 * 出现问题：比如当A线程执行后num=1，唤醒其他线程，此时C线程被唤醒开始执行，发现num！=0，就阻塞等待
 * 此时B获得资源执行后num=0，唤醒其他线程，A获得资源执行后num=1，唤醒其他线程
 * 注意：此时正好唤醒了C，而C在之前是阻塞在了wait()方法这里，唤醒后将继续往下执行，就num++，num=2
 * 由此就出现了虚假唤醒（其本质是wait()在哪阻塞就在哪唤醒）
 * 解决方法：wait()使用while()来判断，不能用if()
 */
class Share{
    private int num=0;
    public synchronized void incr() throws InterruptedException {
//        if(num != 0){
        while(num != 0){
            this.wait();
        }
        num++;
        System.out.println("当前线程："+Thread.currentThread().getName() + ",num=" + num);
        this.notifyAll();
    }
    public synchronized void decr() throws InterruptedException {
//        if(num != 1){
        while(num != 1){
            this.wait();
        }
        num--;
        System.out.println("当前线程："+Thread.currentThread().getName() + ",num=" + num);
        this.notifyAll();
    }
}
public class SpuriousWakeup {
    public static void main(String[] args) {
        Share share = new Share();

        new Thread(() -> {
            for(int i=0; i<=10; i++){
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for(int i=0; i<=10; i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for(int i=0; i<=10; i++){
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for(int i=0; i<=10; i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
