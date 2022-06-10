package inheritance;

/**
 * 子类可以继承父类的所有属性方法，包括private
 */
public class ExtendsText {
    public static void main(String[] args) {
        Student s1 = new Student();
        //子类可以通过父类的get\set方法来操作父类的private属性
        s1.setName("jake");
        System.out.println(s1.getName());
        s1.age = 10;
        s1.stuNo = 10001;
        //子类可以通过父类的public方法来调用父类的private方法
        s1.eat();
    }
}

class Person{
    int age;
    private String name;

    public void eat(){
        System.out.println("吃饭");
        sleep();
    }
    private void sleep(){
        System.out.println("睡觉");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
class Student extends Person{
    int stuNo;

    public void study(){
        System.out.println("学生学习");
    }
}
