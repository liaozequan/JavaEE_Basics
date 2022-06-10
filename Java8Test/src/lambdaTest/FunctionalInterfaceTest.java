package lambdaTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 4大核心函数式接口练习(consumer，predicate)
 */
public class FunctionalInterfaceTest {
    @Test
    public void test1(){
        //一般写法
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("购买了 " + aDouble + "元");
            }
        });

        //lambda写法
        happyTime(999, (aDouble)->System.out.println("购买了 " + aDouble + "元"));
    }

    public void happyTime(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }

    @Test
    public void test2(){
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> filteredList = new ArrayList<>();
        //一般写法
        filteredList = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("a");
            }
        });
        //lambda写法
        filteredList = filterString(list, (s) -> s.contains("a"));
    }

    public ArrayList<String> filterString(List<String> list, Predicate<String> predicate){
        ArrayList<String> filterList = new ArrayList<>();
        for(String s : list){
            //过滤操作，符合的字符串加入到filterList中
            if(predicate.test(s)){
                filterList.add(s);
            }
        }
        return filterList;
    }
}
