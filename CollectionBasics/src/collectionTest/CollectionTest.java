package collectionTest;

import org.junit.Test;

import java.util.*;

/**ArrayList
 * 1.contains
 * 2.arr.containsAll(Collection c):arr集合中是否包含c集合所有元素
 * 3.remove:如果不重写equal，仍然根据地址值删除元素，重写了就根据对象属性匹配删除
 * 4.arr.removeAll(Collection c)：arr=arr-(arr∩c)
 * 5.arr.retainAll(Collection c):arr=arrUc
 * 6.Arrays.asList(<T>[]):数组--->集合
 */
public class CollectionTest {
    @Test
    public void testArrayList(){
        Collection arrayList = new ArrayList();
        arrayList.add(new Person("as", 15));
        /**contains：底层使用object.equal-->"=="来匹配arrayList是否存在括号内要找的对象
        /*即遍历arrayList中所有对象的地址是否等于括号内要找的对象的地址
         如果希望contains匹配的标准是对象里属性是否相等，则可以通过重写对象的equal方法，不使用object.equal
         */
        System.out.println(arrayList.contains(new Person("as", 15)));
    }

    @Test
    public void arrayListRemove(){
        Collection arrayList = new ArrayList();
        arrayList.add(123);
        arrayList.add(123);
        arrayList.add(545);
        Collection arrayList1 = new ArrayList();
        arrayList1.add(123);
        arrayList.remove(arrayList1);  //只删除一个123
        arrayList.removeAll(arrayList1);  //删除所有123
        System.out.println(arrayList);
    }


    @Test
    public void arrayListAslist(){
        List<String> stringList = Arrays.asList(new String[]{"aa", "bb", "cc"});
        System.out.println(stringList);
    }

}
class Person implements Comparable{
    private String name;
    private Integer id;

    public Person(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //重写equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(id, person.id);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Person){
            Person person = (Person) o;
            return this.name.compareTo(person.name);
        }else{
            throw new RuntimeException("比较的类型不匹配");
        }
    }
}
