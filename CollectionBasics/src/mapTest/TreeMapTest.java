package mapTest;


import org.junit.Test;

import java.util.Set;
import java.util.TreeMap;

/**
 *向TreeMap添加<key,value>，要求key必须由同一个类创建的对象
 * 因为要以key来排序，自然排序/定制排序
 */
public class TreeMapTest {
    @Test
    public void test(){
        TreeMap treeMap = new TreeMap();
//        treeMap.put(new Person("jack", 23),null);
//        treeMap.put(new Person("jack", 33),null);
//        treeMap.put(new Person("tom", 13),null);
        Set keySet = treeMap.keySet();
        for(Object o : keySet){
            System.out.println(o);
        }

    }

}
