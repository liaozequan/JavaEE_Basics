package polymorphism;

public class PolymExample {
    public static void main(String[] args) {
        func(new Dog());
        func(new Cat());
        Animal a1 = new Dog();
        /**
         * 这里a1的id是animal的id，而不是dog的id，因此多态性只适用于方法，不适用于属性
         */
        System.out.println(a1.id);
        System.out.println(a1.getClass().getSuperclass());

    }

    public static void func(Animal animal){
        animal.eat();
        animal.shout();
    }
}

class Animal{
    int id = 1001;
    public void eat(){
        System.out.println("动物进食");
    }
    public void shout(){
        System.out.println("动物叫");
    }
}

class Dog extends Animal{
    int id = 2002;
    public void eat(){
        System.out.println("狗吃骨头");
    }
    public void shout(){
        System.out.println("狗叫");
    }
}

class Cat extends Animal{
    public void eat(){
        System.out.println("猫吃鱼");
    }
    public void shout(){
        System.out.println("猫叫");
    }
}