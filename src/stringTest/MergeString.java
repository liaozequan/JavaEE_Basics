package stringTest;

/**
 * String不同拼接方法在内存中的分配
 * 1.常量与常量拼接，地址仍然执行常量池（s3 == s4 true）（final修饰的变量也是常量）
 * 2.常量与变量拼接，会在堆中新建一个对象，再指向常量池（s5 == s3 false）
 * 3.inter()一定返回常量池中的字符串
 */
public class MergeString {
    public static void main(String[] args) {
        String s1 = "java";
        String s2 = "ee";
        String s3 = "javaee";
        String s4 = "java" + "ee";
        String s5 = s1 + "ee";
        String s6 = s1 + s2;
        System.out.println(s3 == s4);   //true
        System.out.println(s3 == s5);   //false
        System.out.println(s3 == s6);   //false
        System.out.println(s5 == s6);   //false

        String s7 = s5.intern();    //inter()一定返回常量池中的，因此s7地址直接指向常量池中的字符串
        System.out.println(s3 == s7);   //true

        s7.concat(s5);

    }
}
