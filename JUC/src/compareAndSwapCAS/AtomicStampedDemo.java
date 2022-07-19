package compareAndSwapCAS;

import java.util.concurrent.atomic.AtomicStampedReference;

/**AtomicStampedReference对象
 * 存一个对象和一个版本号
 * 可以解决ABA问题
 */
class Book{
    private int id;
    private String BookName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public Book(int id, String bookName) {
        this.id = id;
        BookName = bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", BookName='" + BookName + '\'' +
                '}';
    }
}
public class AtomicStampedDemo {
    public static void main(String[] args) {
        Book javaBook = new Book(1, "java");
        //new AtomicStampedReference<>(javaBook, 1)
        //表示实例化AtomicStampedReference，初始对象为javaBook，初始编号为1
        AtomicStampedReference<Book> stampedReference =
                new AtomicStampedReference<>(javaBook, 1);
        System.out.println(stampedReference.getReference().toString() +"，编号："+ stampedReference.getStamp());

        Book mysqlBook = new Book(2, "mysql");
        //compareAndSet(期望值，更新值，期望编号，更新编号)
        boolean b = stampedReference.compareAndSet(javaBook, mysqlBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b + "--" + stampedReference.getReference().toString() +"，编号："+ stampedReference.getStamp());

        b = stampedReference.compareAndSet(mysqlBook, javaBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b + "--" + stampedReference.getReference().toString() +"，编号："+ stampedReference.getStamp());

    }
}
