package threadLocal;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 */
class MyObject{
    @Override
    protected void finalize() throws Throwable {
        //finalize()的调用一般在对象被不可撤销的丢弃之前执行的操作
        System.out.println("--evoke finalize()");
    }
}
public class ReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        MyObject myObject = new MyObject();
        //实例化引用队列
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        //实例化虚引用对象
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject, referenceQueue);
        //虚引用对象get()始终返回null
        System.out.println(phantomReference.get());

        List<byte[]> list = new ArrayList<>();
        new Thread(()->{
            while (true){
                list.add(new byte[1*1024*1024]);
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(phantomReference.get() + ":" + "list add ok");
            }
        }, "t1").start();

        new Thread(()->{
            while (true){
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if(reference != null){
                    System.out.println("有虚引用对象加入了引用队列");
                    break;
                }
            }
        }, "t2").start();
    }

    /**
     * 强引用：普通对象都是强引用,OutOfMemory也不会被回收
     */
    public static void reference(){
        MyObject myObject = new MyObject();
        System.out.println("gc before:" + myObject);
        myObject = null;
        System.gc();//人工开启垃圾处理，一般不这样操作
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("gc after:" + myObject);
    }
    /**
     * 软引用：内存够就不回收，内存不够就回收
     */
    public static void softReference(){
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.out.println("softReference--" + softReference.get());//得到MyObject对象
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("调用gc后，内存够用情况下，不回收软引用对象" + softReference.get());
    }

    /**
     * 弱引用：只要调用垃圾回收，不够内存够不够用，弱引用对象都会被回收
     */
    public static void weakReference(){
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("gc before:" + weakReference.get());
        System.gc();//人工开启垃圾处理，一般这样操作
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("gc after:" + weakReference.get());
    }
    /**
     * 虚引用：调用get()始终返回null
     * 虚引用一定要和引用队列组合，如果虚引用对象被回收，则会进入引用队列
     */
}
