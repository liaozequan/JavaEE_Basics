package collectionTest;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *  collection接口：单列集合，用于存储一个个对象
 *      |--Set接口：存储无序，不可重复数据（数学上定义的集合）
 *          |--HashSet:Set接口的主要实现类；线程不安全；可以存储null
 *              |--LinkedHashSet:HashSet链表结构，双向链表记录添加顺序，对于频繁的遍历，效率比HashSet高
 *          |--TreeSet:二叉红黑树，可以按照添加对象的指定属性排序（实现comparable/comparator接口）
 *  1.Set接口没有额外定义新方法，使用的都是collection接口中的方法
 *  2.HashSet底层结构使用HashMap，HashSet的值存在HashMap的key中
 */

public class SetTest {
    /**Set接口：存储无序，不可重复数据
     * 1.无序性：不是随机存储，是根据哈希值在哈希表中位置来添加
     * 2.不可重复性：以equal()判断，相同的元素只添加一次。
     *             类的话，如果重写了hashCode()和equal()方法，则同属性对象只添加一次。不重写则add几次就加几次
     *             如果hashcode相同，则再用equal()比较是否相同，如果不相同，则以链表方式在哈希表映射的位置加上新元素
     *             如果哈希表使用率超过0.75，则扩容2倍
     */
    @Test
    public void testHashSet(){
        Set set = new HashSet();
        set.add(456);
        set.add(123);
        set.add("asf");
        set.add(new Person("tom", 123));
        set.add(new Person("tom", 123));
        set.add(123);
        for(Object o : set){
            System.out.println(o.toString());
            /*输出： Person{name='tom', id=123}
                    asf
                    Person{name='tom', id=123}
                    456
                    123
             */
        }
    }

    @Test
    public void testLinkedHashSet(){
        Set set = new LinkedHashSet();
        set.add(456);
        set.add(123);
        set.add("asf");
        set.add(new Person("tom", 123));
        set.add(new Person("tom", 123));
        set.add(123);
        //按添加顺序输出
        for(Object o : set){
            System.out.println(o.toString());
            /*输出： 456
                    123
                    asf
                    Person{name='tom', id=123}
             */
        }
    }

    @Test
    public void test1(){
        Set set = new HashSet();
        Person p1 = new Person("AA",11);
        Person p2 = new Person("BB",22);
        set.add(p1);
        set.add(p2);
        p1.setName("CC");
        set.remove(p1);
        System.out.println(set);
        set.add(new Person("CC",11));
        System.out.println(set);
        set.add(new Person("AA", 11));
        System.out.println(set);

    }


}
