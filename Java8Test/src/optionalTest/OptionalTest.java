package optionalTest;

import org.junit.Test;

import java.util.Optional;

/**
 * Optional类测试
 */
public class OptionalTest {
    @Test
    public void test(){
        Girl girl = new Girl();
        //Optional.of(T t) t必须非空
        Optional<Girl> girl1 = Optional.of(girl);
        System.out.println(girl1);

        girl = null;
        //Optional.ofNullable(T t) t可以为空
        Optional<Girl> girl2 = Optional.ofNullable(girl);
        System.out.println(girl2);
    }

    public String getGirlName(Boy boy){
        return boy.getGirl().getName();
    }
    //优化getGirlName(),当这样实现代码冗长
    public String getGirlName1(Boy boy){
        if(boy != null){
            if(boy.getGirl() != null){
                return boy.getGirl().getName();
            }
        }
        return null;
    }
    //使用optional类优化getGirlName()
    public String getGirlNameByOptional(Boy boy){
        //传进来的boy有可能为空，因此使用Optional.ofNullable()
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        //如果boyOptional为空，则实例化orElse()括号内的对象，否则返回boyOptional中的对象
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("lll")));
        //此时boy1一定非空
        Girl girl = boy1.getGirl();
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        Girl girl1 = girlOptional.orElse(new Girl("lll"));
        //此时girl1一定非空
        return girl1.getName();
    }
    @Test
    public void test1(){
        Boy boy = new Boy();
        //调用getGirlName方法，因为boy对象中的girl属性没有实例化，会报空指针异常
//        String name = getGirlName(boy);
//        System.out.println(name);

        System.out.println(getGirlNameByOptional(boy));
    }
}
