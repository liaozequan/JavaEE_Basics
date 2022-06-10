package mapTest;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Map:双列集合，存储<key,value>
 *     |--HashMap(jdk1.2)：Map主要实现类，线程不安全，效率高，可以存储null的key和value
 *                        可以通过collections工具类实现线程安全
 *          |--LinkedHashMap：底层使用双向链表（适合频繁插入删除），遍历顺序是插入的顺序
 *     |--TreeMap：底层红黑树，有序集合，可以按照添加对象的key排序（自然排序，定制排序）
 *                向TreeMap添加<key,value>，要求key必须由同一个类创建的对象
 *     |--HashTable(jdk1.0):古老实现类，线程安全，效率低，不可以存储null的key和value
 *          |--Properties：常用于配置文件，key和value都是String
 *
 *     HashMap底层：数组+链表+红黑树
 *     了解CurrentHashMap
 *     1.key是不可重复且无序,和Set特性相同，因此使用Set存储
 *          -->如果key存储的是一个对象，一定要重写hashCode()和equal()方法
 *     2.value是可重复但无序,使用Collection存储
 *          -->如果value存储的是一个对象，因为containsValue()方法，一定要重写equal()方法
 *     3.当往map中添加元素时，会先将<key,value>封装成Entry，再加入map
 *       因此Entry是不可重复且无序，同样用Set存储
 *
 *
 */
public class MapTest {
    @Test
    public void test(){
        Map map = new HashMap();
        map.put("aa", 123);
        map.put(123,222);
        map.put("bb", 455);
        //获取所有key
        Set keySet = map.keySet();
        for(Object o : keySet){
            System.out.println(o);
        }
        //获取所有value
        Collection values = map.values();
        for(Object o : values){
            System.out.println(o);
        }
        //获取所有<key,value>
        //方法一
        Set entrySet = map.entrySet();
        for(Object o : entrySet){
            Map.Entry entry = (Map.Entry) o;
            System.out.println("<"+entry.getKey()+","+entry.getValue()+">");
        }
        //方法二：先获取key，再用key找value
        for(Object o : keySet){
            System.out.println("<"+o+","+map.get(o)+">");
        }
    }
}
