package threadLocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 需求1：5个销售卖房子，总公司只关心总销售量
 * 需求2：5个销售卖房子，总公司统计每个销售的销售量
 */
class House{
    int saleCount = 0;
    public synchronized void incr(){
        saleCount++;
    }
    //这种ThreadLocal实例化不推荐，代码冗长
//    ThreadLocal<Integer> saleVolume = new ThreadLocal<Integer>(){
//        @Override
//        protected Integer initialValue() {
//            return 0;
//        }
//    };
    //推荐使用这种方法实例化
    //每个线程都会有自己的saleVolume局部变量
    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(()->0);
    public void incrByThreadLocal(){
        saleVolume.set(saleVolume.get() + 1);
    }
}
public class ThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        House house = new House();
        for (int i = 1; i <= 5; i++) {
            new Thread(()->{
                int size = new Random().nextInt(5)+1;
                try {
                    for (int j = 0; j < size; j++) {
                        house.incr();
                        house.incrByThreadLocal();
                    }
                    System.out.println(Thread.currentThread().getName() + "号销售卖出" + house.saleVolume.get() + "套房");
                } finally {
                    //每个线程使用完ThreadLocal局部变量后，必须remove()，特别在线程池下，线程容易被复用
                    //不及时remove，容易导致内存泄露(上一个线程的值，下一次复用这个线程，这个值还在)
                    house.saleVolume.remove();
                }
            }, String.valueOf(i)).start();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + ":总销售量:" + house.saleCount);
    }
}
