package Generic;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 1.泛型必须是类或者包装类，不能是基本数据类型
 * 2.静态方法内不能有泛型,泛型方法可以声明为静态
 * 3.异常类不能是泛型类
 */
public class Generic {
    @Test
    public void test1(){
//        ArrayList<int> list = new ArrayList<int>(); 错误
        ArrayList<Integer> list = new ArrayList<>();


    }

}
