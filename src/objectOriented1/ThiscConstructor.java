package objectOriented1;


/**
 * this调用构造器
 * 1.无参构造器中不能用this调用其他有参构造器
 * 2.在有参构造器中用this调用本类中其他构造器时，代码必须放在第一行，因此一个构造器只能调用一个其他构造器
 * 3.不管调用多少个构造器，都只实例化一个对象
 * 4.n个构造器，只会有n-1个构造器使用this调用其他构造器
 */
public class ThiscConstructor {
    public static void main(String[] args) {
        Person person1 = new Person(11, "jake");
        System.exit(1);
    }
}

class Person{
    int age;
    String name;

    Person(){
        //this();
        System.out.println("无参构造器");
    }
    Person(int age){
//        System.out.println("一个参数构造器");
        this(); //this调用构造器必须放在第一行
        System.out.println("一个参数构造器");
        this.age = age;
    }
    Person(int age, String name){
        this(age);
        System.out.println("2个参数构造器");
        this.name = name;

    }
}
