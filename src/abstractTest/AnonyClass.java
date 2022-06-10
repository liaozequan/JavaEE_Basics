package abstractTest;

/**
 * 匿名对象
 * 匿名类：使抽象类能被实例化（但实质是实例化了子类，但这个子类只继承抽象父类，不新增任何属性和方法）
 */
public class AnonyClass {
    public static void main(String[] args) {
        //非匿名类非匿名对象
        Student s1 = new Student();
        method(s1);

        //匿名对象，实例化了对象，但没有声明变量赋予对象地址
        method(new Student());

        /**匿名类非匿名对象:使抽象类能被实例化（但实质是实例化了子类，但这个子类只继承抽象父类，不新增任何属性和方法）
         * Person是抽象类，无法被实例化
         * 此时children是Person的一个子类的实例化对象，可以在大括号中重写父类方法
         * 在大括号中新增任何属性和方法，都无法调用，因为声明的类始终是父类
         */
        Person children = new Person() {
            int id =  0;
            @Override
            public void walk() {
            }
            @Override
            public void breath() {
            }
        };
        method(children);

        /**
         * 匿名类匿名对象
         */
        method(new Person() {
            @Override
            public void walk() {
            }
            @Override
            public void breath() {
            }
        });
    }

    public static void method(Person person){

    }
}


