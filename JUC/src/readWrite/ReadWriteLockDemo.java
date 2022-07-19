package readWrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁demo
 * 读锁：共享锁  写锁：独占锁
 * 一个资源可以同时被多个读线程访问，或可以被一个写线程访问，
 * 但不能同时存在读锁和写锁，读写是互斥的，写写也是互斥的，读读是共享的
 *
 * 读写锁的缺点：
 *  1.读的时候，不能写，只有读完，才能写
 *  2.造成锁饥饿，一直读，没有写操作
 */
//资源类
class MapCache{
    //创建map，模拟缓冲
    private Map<String, Object> map = new HashMap<>();
    //创建读写锁
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    //写数据
    public void put(String key, Object value){
        //写数据之前，添加写锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "::正在写" + "key:" + key);
            //暂停一会，模拟写操作要耗时
            TimeUnit.MICROSECONDS.sleep(300);
            //写数据
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "::写完了" + "key:" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放写锁
            rwLock.writeLock().unlock();
        }
    }
    //取数据
    public Object get(String key){
        //取数据之前，先加读锁
        rwLock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + "::正在读取" + "key:" + key);
            //暂停一会，模拟读操作要耗时
            TimeUnit.MICROSECONDS.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "::读取完了" + "key:" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放读锁
            rwLock.readLock().unlock();
        }
        return result;
    }
}
public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        MapCache mapCache = new MapCache();
        /**
         * 不使用读写锁，会出现问题：
         * 正在写的数据，因为写是有耗时的，在写的过程中就已经可以被读取了
         */
        //创建线程写数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(()->{
                mapCache.put(num+"", num+"");
            }, String.valueOf(i)).start();
        }

        //创建线程读数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(()->{
                mapCache.get(num+"");
            }, String.valueOf(i)).start();
        }
    }
}
