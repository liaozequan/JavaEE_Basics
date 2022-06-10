package Generic;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 泛型继承问题
 * 1.A是B的父类，B可以赋值给A
 *   class<A>,class<B>,A是B的父类，当class<B>不能赋值给class<A>
 *
 */
public class GenericInherit {
    @Test
    public void test(){
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Object> list2 = new ArrayList<>();
        //list2 = list1;    虽然String是Object的子类，但是String的泛型list不能赋给Object的泛型list

        show(list1);
        show1(list2);
    }
    public <T> void show(ArrayList<T> list){
        return;
    }
    //也可以是用通配符?
    public void show1(ArrayList<?> list){
        return;
    }
}
