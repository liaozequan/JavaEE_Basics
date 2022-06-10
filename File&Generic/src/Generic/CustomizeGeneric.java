package Generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义泛型：泛型类、泛型接口;泛型方法
 */
public class CustomizeGeneric {
    @Test
    public void test1(){
        Order<Integer> order = new Order<Integer>();
        //order.setOrderT("a"); OrderT指定了integer类型，不能为String
        order.orderT = 123;
    }

    @Test
    public void test2(){
        //1.子类继承父类（泛型类）时指定了父类的泛型类型
        /**2.如果子类继承父类（泛型类）时未指定了父类的泛型类型,
            则子类也必须是泛型类,且如果父类泛型名字是"T"，子类也必须是"T"
         */
        SubOrder<Double> subOrder = new SubOrder<Double>();
        subOrder.orderT = 12d;
    }
}

class Order<T>{
    String orderName;
    int orderId;
    T orderT;

    public Order() {
    }

    /**泛型方法：方法中的泛型与类泛型无关，名字都不同，且在方法前必须声明"<E>",不然编译器会认为E是一个具体的类
     * 可以声明为静态泛型方法
     * 功能：将传进来未知类型的数组转换成该类型List返回
     */
    public static  <E> List<E> copyFromArrayList(E[] arr){
        ArrayList<E> arrayList = new ArrayList<>();
        for(E e : arr){
            arrayList.add(e);
        }
        return arrayList;
    }
}

class SubOrder<T> extends Order<T>{
    int subOrderId;
}
