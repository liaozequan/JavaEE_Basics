package inheritance;

/**
 * super的使用：
 *  1.super可以理解为：父类的
 *  2.super可以调用父类的属性，方法，构造器
 *  3.super调用属性、方法:
 *      3.1当子类中有重名的属性，或重写了父类的方法，此时是调用子类的属性或方法，如果想在子类中调用父类的属性或父类中被重写的方法，就可以用super
 *
 *  4.super调用构造器:
 *      4.1在子类的构造器中使用“super(形参列表)” 的方式来调用父类的构造器
 *      4.2在子类的构造器中，“this(形参列表)”和“super(形参列表)”只能有一个，因为this和super调用构造器都需要放在<第一行>
 *      4.3如果父类中定义了有参构造器，而没有无参构造器，当子类的构造器中没有用super调用父类的构造器，此时子类的构造器中默认调用
 *      父类的无参构造器，而又因为父类没有无参构造器，因此在子类的构造器中会报错。（There is no default constructor available in 'inheritance.Person2'）
 *      4.4在子类的多个构造器中，至少有一个构造器是通过super()调用了父类构造器，因为n个构造器，只会有n-1个构造器使用this调用其他构造器
 *      而剩下的一个构造器就一定是调用super()
 *      4.5调用一个构造器不一定就会实例化一个对象，因为有可能是子类调用父类的构造器来创建子类对象
 */
public class SuperTest {
    public static void main(String[] args) {
        Student2 s2 = new Student2(1);
        s2.show();
        s2.eatTest();
    }
}

class Person2{
    int age;
    String name;
    int id = 350123; //人的身份证号码



    public void eat(){
        System.out.println("人吃饭");
    }
    private void sleep(){
        System.out.println("人睡觉");
    }

}
class Student2 extends Person2{
    int id = 211;   //学生的学号，与父类的身份证号重名，但属性不存在重写
    public void eat(){
        System.out.println("学生吃饭");
    }

    public Student2() {
    }

    public Student2(int id) {
        super();
        this.id = id;
        //super();必须放在第一行
    }

    public void eatTest(){
        eat();
        super.eat();
    }

    public void show(){
        this.age = 1;
        super.age = 2;
        System.out.println(this.age);//输出2
        //继承的属性如name和age，this.age和super.age是一样的
        System.out.println("name:"+name + " age:"+age);
        System.out.println("学号:"+this.id);
        //通过super来调用父类的id属性
        System.out.println("身份证号:" + super.id);
    }
}
