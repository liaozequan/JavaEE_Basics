package comparableTest;

import org.junit.Test;

import java.util.Arrays;

/**
 * 对象之间的比较
 * 实现comparable接口
 * 1.像String，包装类等实现了comparable接口，重写了compareTo(obj)方法，定义了比较两个对象大小的方法
 * 2.重写compareTo(obj)规则：
 *      1.如果当前对象this > 形参对象obj，返回正整数
 *      2.如果当前对象this < 形参对象obj，返回负整数
 *      3.如果当前对象this == 形参对象obj，返回0
 * 3.自定义类需要排序时，可以实现comparable接口，并在compareTo(obj)方法声明如何排序
 */
public class ComparableTest {
    @Test
    public void test1(){
        String[] arr = new String[]{"aa","zz","gg","bb"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));//[aa, bb, gg, zz]
        //String之间可以按字母大小排序，是因为string实现了comparable接口
    }

    @Test
    public void test2(){
        Produce[] produces = new Produce[3];
        produces[0] = new Produce("cheer", 15);
        produces[1] = new Produce("computer", 150);
        produces[2] = new Produce("mouse", 30);
        Arrays.sort(produces);
        System.out.println(Arrays.toString(produces));

    }
}

class Produce implements Comparable{
    public String name;
    public int price;

    public Produce(String name, int price) {
        this.name = name;
        this.price = price;
    }

    //声明对象间排序规则:按价格非递减
    @Override
    public int compareTo(Object o) {
        if(o instanceof Produce){
            Produce produce = (Produce) o;
            //方式1
            if(this.price > produce.price){
                return 1;
            }else if(this.price < produce.price){
                return -1;
            }else{
                return 0;
            }
            //方式2
            //return  Integer.compare(this.price, produce.price);
        }else{
            throw new RuntimeException("比较的数据类型不一致");
        }
    }

    @Override
    public String toString() {
        return "Produce{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
