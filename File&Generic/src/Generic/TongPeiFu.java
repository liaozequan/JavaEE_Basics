package Generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 通配符:  ?
 */
public class TongPeiFu {
    @Test
    public void test(){
        List<String> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();

        List<?> list = new ArrayList<>();
        list = list1;

        show(list1);
        show(list2);
        list1.add("123");
        //通配符类型的添加：无法添加，当可以加null
//        list.add("123"); 报错
        list.add(null);

        //读取：允许读取数据，但返回类型为Object
        Object o = list.get(0);
    }

    public void show(List<?> list){
        return;
    }

    /**
     * 通配符限制条件
     * ? extents classA
     * ? super classB
     */
    public void test1(){
        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;

        List<Student> list3 = null;
        List<Person> list4 = null;
        List<Object> list5 = null;

        list1 = list3;
        list1 = list4;
        //list1 = list5; 报错，说明<? extends Person>只能接收<=Person的类

        //list2 = list3; 报错，说明<? super Person>只能接收>=Person的类
        list2 = list4;
        list2 = list5;
    }
}

class Person{

}

class Student extends Person{

}
