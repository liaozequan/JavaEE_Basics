package reflect;

public class Person {
    private String name;
    public int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void show(){
        System.out.println("Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}');
    }

    private void privateM(String s){
        System.out.println("私有方法"+s);
    }

    private String getReturn(String s){
        System.out.println("调用方法getReturn");
        return s;
    }

    private static void staticM(){
        System.out.println("调用静态方法");
    }
}
