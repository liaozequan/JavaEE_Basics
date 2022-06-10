package stringTest;

/**
 * 理解为什么String是不可变的
 * private final char value[]; String中将char数组声明为final
 * 1.当字符串重新赋值时，需要重新分配内存给新的值
 * 2.拼接、replace字符串都需要重新分配内存来赋值
 * final的意义就是已存在的字符串无法被修改，新的值只能通过分配新内存来定义
 */
public class ImmutableString {
    public static void main(String[] args) {
        String s1 = "abc";  //字面量 来定义String类型变量
        String s2 = "abc";
        //此时s1,s2地址相同（参考笔记：String堆栈方法区）
        System.out.println(s1 == s2);   //输出true，即表示地址值相同
        s2 = "edf";
        System.out.println(s1 == s2);   //输出false，即表示地址值不同
        System.out.println(s1);         //s1仍然为"abc"
        //s2修改为"edf"后，在常量池中新增了"edf"，s2地址指向它，此时常量池同时存在（"abc","edf"）
        String s3 = "abc";
        System.out.println(s3 == s1);   //输出true，即表示地址值相同
        s3 += "de";     //s3变为"abcde"，又在常量池新增了"abcde"，此时常量池同时存在（"abc","edf","abcde"）
        String s4 = s3;
        s4 = s4.replace('a', 'z');
        System.out.println(s3);     //"abcde"
        System.out.println(s4);     //"zbcde",此时常量池同时存在（"abc","edf","abcde","zbcde"）
    }
}
