package collectionTest;
import org.junit.Test;
import java.util.ArrayList;

/**
 *  collection接口：单列集合，用于存储一个个对象。<key,value>双列集合
 *      |--List接口：存储有序，可重复数据（动态数组）
 *          |--ArrayList:作为List接口的主要实现类（java1.2），线程不安全，效率高,可以通过collections工具类实现线程安全；底层使用object[]存储
 *          |--LinkedList（java1.2）：底层使用双向链表（适合频繁插入删除）
 *          |--Vector：List接口的比较旧的实现类（java1.0），线程安全，效率低；底层使用object[]存储
 */

/**ArrayList源码分析
 * jdk7：
 *    1.ArrayList arrayList = new ArrayList();//创建长度为10的object[]数组
 *    2.arrayList.add(123)//object[0] = new Integer(123);
 *    3.添加的元素多于10的时候，则扩容至原来的1.5倍
 * jdk8
 *    1.ArrayList arrayList = new ArrayList();//object[]{}数组，无长度
 *    2.第一次调用add()，才扩容至10
 *    3.容量不够与jdk7一样
 * jdk7，8对比：jdk8延迟数组创建，节省内存（类似于懒汉式）
 */

/**LinkedList源码分析：同双向链表
 *
 * vector源码分析：new Vector();//创建长度为10的object[]数组
 *               添加的元素多于10的时候，则扩容至原来的2倍
 */
public class ListTest {
    @Test
    public void test(){
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.remove(2);//结果为arrayList={1,2}
        arrayList.remove(new Integer(2));//结果为arrayList={1,3}
        /**remove(int index)删除指针index的元素
         * remove(object obj)删除obj元素
         */

    }
}
