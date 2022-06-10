package objectOriented1;

/**
 * 可变个数的形参：允许直接定义 能和多个实参相匹配的形参
 * 1.形参格式：[数据类型]... [参数名]
 * 2.传入实参个数任意,也可以传数组，但数据类型一定要与定义的形参一致.
 * 3.定义了可变个数的形参方法，形参为相同数据类型的数组的重载方法就不能定义
 *      如String... 与String[]不能同时作为两个重载方法的形参
 * 4.如果一个方法有多个形参，则可变个数形参必须放在末尾，
 *      正是因为必须放末尾，因此一个方法的形参中只能有一个可变个数形参
 */
public class VarargsTest {
    public static void main(String[] args) {
        VarargsTest varargsTest = new VarargsTest();
        varargsTest.show(123);
        varargsTest.show("abc");
        varargsTest.show("hello","world");
        varargsTest.show(new String[]{"aa","bb"});
        //除了重载的方法，其他类型的实参都是调用 可变个数的形参 的方法
        varargsTest.show();
        String a = new String();

    }

    public void show(int i){
        System.out.println(i);
    }

    public void show(String s){
        System.out.println(s);
    }

    //[数据类型]... [参数名]
    public void show(String...s){
        System.out.println("调用可变个数的形参方法 String...s");
        //传进来的是数组
        for(int i=0; i<s.length; i++){
            System.out.print(s[i]);
        }
    }

//    public void show(String[] s){
//    }

    /**
     * 如果有多个形参，则可变个数形参必须放在末尾，
     * 正是因为必须放末尾，因此一个方法的形参中只能有一个可变个数形参
     */
    public void show(int j, String...s){
        System.out.println("调用可变个数的形参方法 String...s");
        //传进来的是数组
        for(int i=0; i<s.length; i++){
            System.out.print(s[i]);
        }
    }
}
