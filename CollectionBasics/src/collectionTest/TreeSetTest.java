package collectionTest;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;
/**TreeSet主要提供排序
 * 因此需要插入相同对象
 */
public class TreeSetTest {
    /**
     * 自然排序（实现comparable接口）：
     *      比较两个对象是否相等标准：compareTo()返回值0，不再是equal()
     */
    @Test
    public void testTreeSet(){
        TreeSet<Person> treeSet = new TreeSet();
        treeSet.add(new Person("jack", 23));
        treeSet.add(new Person("jack", 33));
        for(Person p : treeSet){
            System.out.println(p.toString());
        }
        /*输出：Person{name='jack', id=23}
            Person类实现了comparable接口，重写了compareTo()方法
            通过name属性来比较排序，当name都是"jack"时，TreeSet认为两个对象是相同的，不考虑不参与排序的id属性
         */
    }

    /**
     * 定制排序（实现Comparator接口）：
     *      比较两个对象是否相等标准：compareTo()返回值0，不再是equal()
     */
    @Test
    public void testTreeSet1(){
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Person && o2 instanceof Person){
                    Person p1 = (Person) o1;
                    Person p2 = (Person) o2;
                    return -p1.getId().compareTo(p2.getId());
                }else{
                    throw new RuntimeException("比较的类型不匹配");
                }
            }
        };
        TreeSet<Person> treeSet = new TreeSet(comparator);
        treeSet.add(new Person("jack", 23));
        treeSet.add(new Person("jack", 33));
        treeSet.add(new Person("tom", 13));
        for(Person p : treeSet){
            System.out.println(p.toString());
        }
    }
}
