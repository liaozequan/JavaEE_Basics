package polymorphism;

import java.util.Objects;

public class EqualTest {
    public static void main(String[] args) {

        String a = "123";
        String b = "123";
        System.out.println(a==b);   //true ab指向方法区字符串常量池的同一个“123”
        String c = new String("123");
        String d = new String("123");
        System.out.println(c==d);   //false cd指向堆空间不同对象实体
        System.out.println(c==a);   //false c指向堆空间的对象实体，a指向方法区的字符串常量池

        Student s1 = new Student(15,1001);
        Student s2 =new Student(15, 1001);
        /**
         * object中的equal方法仍然是==，仍然比较地址值
         * public boolean equals(Object obj) {
         *         return (this == obj);
         *     }
         */
        System.out.println(s1.equals(s2));  //false
        /**
         * String中的equal方法被重写了，比较字符串内容是否一致
         * String、Date、File、包装类等都重写了Object.equal的方法
         */
        System.out.println(c.equals(d));    //true

    }
}
class Student{
    int age;
    int id;

    public Student(int age, int id) {
        this.age = age;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, id);
    }
}
