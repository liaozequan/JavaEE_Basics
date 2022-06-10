package lambdaTest;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造器引用
 */
public class ConstructorReferenceTest {
    /**空参构造器引用
     * Supplier: T get()
     * Person:   Person Person()
     */
    @Test
    public void test(){
        //一般写法
        Supplier<Person> supplier = new Supplier<Person>() {
            @Override
            public Person get() {
                return new Person();
            }
        };
        //lambda写法
        Supplier<Person> supplier1 = () -> new Person();
        Person p1 = supplier1.get();
        //构造器引用
        Supplier<Person> supplier2 = Person :: new;
        Person p2 = supplier2.get();
    }

    /**单个参 构造器引用
     * Function<Integer, Person>: Person apply(Integer id)
     * Person:                    Person Person(Integer id)
     */
    @Test
    public void test2(){
        //一般写法
        Function<Integer, Person> function = new Function<Integer, Person>() {
            @Override
            public Person apply(Integer id) {
                return new Person(id);
            }
        };
        //lambda写法
        Function<Integer, Person> function1 = id -> new Person(id);
        Person p1 = function1.apply(111);
        //构造器引用
        Function<Integer, Person> function2 = Person::new;
        Person p2 = function2.apply(150);
    }

    /**两个参构造器引用
     * BiFunction<Integer, String, Person>: Person apply(Integer id, String name)
     * Person:                              Person Person(Integer id, String name)
     */
    @Test
    public void test3(){
        //一般写法
        BiFunction<Integer, String, Person> biFunction = new BiFunction<Integer, String, Person>() {
            @Override
            public Person apply(Integer id, String name) {
                return new Person(id, name);
            }
        };
        //lambda写法
        BiFunction<Integer, String, Person> biFunction1 = (id, name) -> new Person(id, name);
        biFunction1.apply(15, "as");
        //构造器引用
        BiFunction<Integer, String, Person> biFunction2 = Person::new;
        biFunction2.apply(231, "afsdf");

    }

}

class Person{
    private int id;
    private String name;

    public Person() {
    }

    public Person(int id) {
        this.id = id;
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
}