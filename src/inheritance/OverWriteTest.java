package inheritance;

/**
 * 方法重写规定：
 *  1.子类重写的方法的权限修饰符 不小于 父类方法的权限修饰符
 *      1.1子类不能重写父类private方法
 *  2.重写方法的返回值类型：
 *      2.1父类被重写方法的返回值类型是void，那么子类重写的方法返回值也必须是void
 *      2.2引用数据类型：父类被重写方法的返回值类型是A类，那么子类重写的方法返回值可以是 A类 或 A类的子类
 *      2.3基本数据类型：父类被重写方法的返回值类型是基本数据类型（如int），那么子类重写的方法返回值必须相同（int）
 *  3.子类重写的方法所抛出的异常类型不大于父类被重写方法所抛出的异常类型
 *  4.如果父类中有static方法，子类中也想写一个同名、同参数列表的方法，这也需要是static方法，但子类中的方法并不算是父类方法的重写！
 *    因此，static方法无法被重写！因为static方法和属性是随着类的加载而加载的，不能被重写
 *  5.从下面代码可以看出，如果子类调用了从父类继承的方法sleep()（未重写），而这个sleep方法又调用了一个被子类重写的方法study()，
 *  和一个未被子类重写的方法walk（因为是private方法），在运行时study因为被子类重写而被覆盖为子类重写的方法，当时walk仍然是调用父类的方法（因为没有被重写）
 */
public class OverWriteTest {
    public static void main(String[] args) {
        Person1 p1 = new Person1();
        Student1 s1 = new Student1();
        //p1.sleep();
        s1.sleep();
    }
}

class Person1{
    int age;

    void sleep(){
        System.out.println("人睡觉");
        study();
        walk();
    }
    public Object work()throws Exception{
        return null;
    }
    public static void eat(){

    }
    public void study(){
        System.out.println("人学习");
    }
    private void walk(){
        System.out.println("人走路");
    }
}
class Student1 extends Person1{
    int stuNo;

//    public void sleep(){
//        System.out.println("学生早睡早起");
//    }

    /**
     * 父类返回值是object，子类返回可以是object的子类
     * 因为string是object的子类，因此可以这样重写
     */
    public String work() throws RuntimeException{
        return null;
    }

    /**
     * 这里并没有重写父类的static eat方法
     */
    public static void eat(){

    }

    public void study(){
        System.out.println("学生学习");
    }
    private void walk(){
        System.out.println("学生走路");
    }
}

