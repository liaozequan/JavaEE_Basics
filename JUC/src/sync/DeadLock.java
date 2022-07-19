package sync;

public class DeadLock {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        new Thread(()->{
            synchronized (o1){
                try {
                    Thread.sleep(1000);
                    System.out.println("以获取o1锁，等待获取o2锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){

                }
            }
        }, "a").start();

        new Thread(()->{
            synchronized (o2){
                try {
                    Thread.sleep(1000);
                    System.out.println("以获取o2锁，等待获取o1锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){

                }
            }
        }, "b").start();

    }
}
