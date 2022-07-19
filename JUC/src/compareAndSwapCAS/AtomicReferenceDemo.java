package compareAndSwapCAS;

import java.util.concurrent.atomic.AtomicReference;

/**既然Integer、double等数据类型都有原子类
 * 那么自定义的类也可以通过原子引用来设为原子类
 *
 */
class User{
    public String name;
    public int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User user1 = new User("tom", 21);
        User user2 = new User("jack", 22);
        atomicReference.set(user1);
        //atomicReference.compareAndSet(期望值, 更新值)
        //如果atomicReference的值于期望值相等，这将atomicReference修改为更新值
        System.out.println(atomicReference.compareAndSet(user1, user2) +
                "--" + atomicReference.get().toString());
    }
}
