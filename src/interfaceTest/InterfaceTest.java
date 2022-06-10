package interfaceTest;

/**
 * 在java中，接口和类是并列的结构
 * 1.定义接口，接口中的成员
 *      全局常量，抽象方法，静态方法，默认方法
 *      接口中不能定义构造器
 *
 * 2.如果一个类实现了接口，这个类重写了接口的所有抽象方法，则这个类就可以被实例化
 *                    这个类重没有重写接口的所有抽象方法，则这个类只能是一个抽象类
 *
 * 3.class A extends B implements C,D,E
 *
 * 4.接口可以 多 继承接口   interface A extends B,C
 */
public class InterfaceTest {
    public static void main(String[] args) {
        System.out.println(Flyable.MIN_SPEED);
        //Flyable.MIN_SPEED = 2;  //Cannot assign a value to final variable 'MIN_SPEED'
        Flyable.come();
    }
}

interface Flyable{
    //全局常量
    public static final int MAX_SPEED = 100;
    //仍然具有static final的性质，无法修改
    int MIN_SPEED = 1;

    //抽象方法
    public abstract void fly();
    //仍然是抽象方法
    void stop();

    //静态方法,实现类无法调用，只能接口自己调用
    public static void come(){
        System.out.println("come");
    }

    /***默认方法，无法被接口自己调用，可以被实现类调用和重写
     * （1）如果一个类实现了 接口，有继承了一个父类，且接口的默认方法和父类的方法 名字、形参相同，
     * 且子类没有重新这个方法，这子类默认调用的是父类的方法
     * （2）如果一个类实现了 多个接口，且这几个接口都定义了 同名同形参的方法，则编译器报错（必须在类中重写方法）
     * （3）如果一个类实现了 接口，有继承了一个父类，且接口的全局常量和父类的属性名相同，则编译器报错
     */
    public default void eat(){
        System.out.println("eat");
    }

}

/**
 * 没有重新Flyable接口的所有方法，只能将Plane定义为抽象类
 */
abstract class Plane implements Flyable{
    @Override
    public void fly() {

    }
    //重写了Flyable接口的默认方法
    public void eat(){
        System.out.println("飞机加油");
        /**
         * 调用接口自己的默认方法
         */
        Flyable.super.eat();
    }

}
