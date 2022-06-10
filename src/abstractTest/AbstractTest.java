package abstractTest;

/**
 * abstract修饰类--抽象类:
 *      1.不能被实例化
 *      2.抽象类中一定有构造器，便于子类实例化时调用
 *      3.抽象类中可以有具体方法和抽象方法
 *      4.abstract不能修饰 final类
 * abstract修饰方法--抽象方法：
 *      1.只有方法声明，没有方法体
 *      2.只有抽象类才能纳声明抽象方法（因为抽象方法无法被调用，而普通类能调用方法，因此只有抽象类不能实例化，也不能调用方法）
 *      3.如果子类继承了有抽象方法的抽象父类（父类可能还有抽象父类），则子类要么也是抽象类，要么重写所有抽象方法
 *      4.abstract不能修饰 private/static/final方法
 */
public class AbstractTest {
    /**
     * 'Person' is abstract; cannot be instantiated
     */
    //Person p1 = new Person();
}
abstract class Creature{
    public abstract void breath();
}
abstract class Person extends Creature{
    String name;
    int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat(){
        System.out.println("People eat");
    }
    public abstract void walk();
}

class Student extends Person{

    public Student() {
    }
    public Student(String name, int age) {
        super(name, age);
    }

    @Override
    public void walk() {

    }

    @Override
    public void breath() {

    }
}