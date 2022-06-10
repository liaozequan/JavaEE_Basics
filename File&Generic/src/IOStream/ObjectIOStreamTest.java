package IOStream;

import org.junit.Test;

import java.io.*;

/**对象流，存储对象数据到硬盘文件
 * 序列化（ObjectOutputStream）：将内存中的java对象转换为二进制流，方便保存到硬盘或进行网络传输
 * 反序列化（ObjectInputStream）：将硬盘或网络传输得到的二进制流文件，转换为java对象
 * 1.要想java对象可序列化，需要满足要求
 *      (1)类实现Serializable接口
 *      (2)类中声明属性 public static final long serialVersionUID=xxxxxxL
 *      如果不显示定义serialVersionUID，jvm会自动添加，但是当已经序列化java对象生成文件后，又对这个对象进行修改，
 *      此时对修改之前已序列化的文件进行反序列化，由于前后java对象serialVersionUID不一致，反序列化将出错
 *      (3)类内部的属性也要保证可序列化，如String（默认情况下基本数据类型可以序列化）
 *      (4)不能序列化被static和transient修饰的属性，虽然序列化和反序列化不会报错，当反序列化后得到的属性值都是null
 */
public class ObjectIOStreamTest {
    /**
     * 序列化过程：将内存中的java对象保存到磁盘或（通过网络传输出去）
     * 使用ObjectOutputStream实现
     */
    @Test
    public void test1(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));
            oos.writeObject(new String("abcd"));
            oos.flush();
            oos.writeObject(new Person(13, "adasd"));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(oos != null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * 反序列化过程：将磁盘文件（或网络流数据）中的对象转换为内存中的java对象
     * 使用ObjectInputStream实现
     */
    @Test
    public void test2(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));
            Object o = ois.readObject();
            String s = (String) o;
            System.out.println(s);
            Person p = (Person) ois.readObject();
            System.out.println(p);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Person implements Serializable{
    public static final long serialVersionUID = 423242342L;
    public int age;
    public String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}