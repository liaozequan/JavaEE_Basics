package comparableTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 实现Comparator接口，自定义比较器
 * 使用场景：
 *      1.当想比较的类没有实现comparable接口，有不想修改这个类来让它实现
 *      2.有的类已经实现了comparable接口，但定义的排序方式不满足当前使用要求
 * 使用：重写compare(Object o1, Object o2)方法,比较o1和o2大小
 *      o1 > o2 返回正整数
 *      o1 < o2 返回负整数
 *      o1 == o2 返回0
 */
public class ComparatorTest {
    @Test
    public void test1(){
        String[] arr = new String[]{"aa","zz","gg","bb"};
        //自定义比较器
        Arrays.sort(arr, new Comparator<String>() {
            //重写compare方法
            @Override
            public int compare(String o1, String o2) {
                if(o1 instanceof String && o2 instanceof String){
                    String s1 = (String) o1;
                    String s2 = (String) o2;
                    return s1.compareTo(s2);
                }
                throw new RuntimeException("比较的数据类型不一致");
            }
        });
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test2(){
        Produce[] produces = new Produce[3];
        produces[0] = new Produce("cheer", 15);
        produces[1] = new Produce("computer", 150);
        produces[2] = new Produce("mouse", 30);
        Arrays.sort(produces, new Comparator<Produce>() {
            @Override
            public int compare(Produce o1, Produce o2) {
                if(o1 instanceof Produce && o2 instanceof Produce){
                    Produce p1 = o1;
                    Produce p2 = o2;
                    //从大到小排序
                    return -Integer.compare(o1.price, o2.price);
                }
                throw new RuntimeException("比较的数据类型不一致");
            }
        });
        System.out.println(Arrays.toString(produces));
    }
}
