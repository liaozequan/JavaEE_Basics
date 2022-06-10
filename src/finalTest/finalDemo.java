package finalTest;

/**
 * final关键字使用：可以修饰 类、方法、变量
 * 1.final修饰类：则此类无法被继承
 *      如：String，System，StringBuffer
 *
 * 2.final修饰方法：则此方法不能被子类重写
 * 3.final修饰变量：变量无法被修改，如c++ const
 *      3.1final修饰属性：只能 1.显示初始化，2.代码块初始化，3.构造器中初始化
 */
public class finalDemo {
    //1.显示初始化
    final int WEIGHT = 0;
    final int HEIGHT;
    final int MONEY;
    //2.代码块初始化
    {
        HEIGHT = 2;
    }
    //3.构造器中初始化
    public finalDemo(){
        MONEY = 2;
    }
    public finalDemo(int n){
        MONEY = n;
    }


}

final class Student{

}
/**
 * 报错，final类 student无法被继承
 */
//final class Postgraduate extends  Student{
//
//}

class Student1{
    final public void study(){

    }
}

/**无法重写类 student1 中final方法 study()
 * study()' cannot override 'study()' in 'finalTest.Student1'; overridden method is final
 */
//class Postgraduate extends  Student1{
//    public void study(){
//
//    }
//}
