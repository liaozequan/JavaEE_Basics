package volatileDemo;

/**
 * volatile的使用：
 * 单例模式反正指令重排序
 */
public class VolatileUseSingletonDemo {
    private static volatile VolatileUseSingletonDemo singleton;

    public static VolatileUseSingletonDemo getInstance(){
        if(singleton == null){
            //1.多线程并发单例模式创建对象时，加锁保证只有一个线程来只实例化一个对象
            synchronized (VolatileUseSingletonDemo.class){
                if(singleton == null){
                    /**隐患：singleton = new VolatileUseSingletonDemo()
                     * 实例化对象分3步（分配内存空间、初始化对象、将对象指向分配的内存空间）
                     * 某些编译期会因为效率问题，进行重排序（分配内存空间、将对象指向分配的内存空间、初始化对象）
                     * 这样某个线程可能会得到一个未完全实例化的对象
                     *
                     *解决隐患：利用volatile禁止重排序
                     */
                    singleton = new VolatileUseSingletonDemo();
                }
            }
        }
        return singleton;
    }
}
