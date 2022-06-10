package stringTest;

/**
 * String-->char[]  toCharArray()
 * char[]-->String  String构造器
 */
public class StringToCharArray {
    public static void main(String[] args) {
        String s1 = "abcd";
        char[] s2 = s1.toCharArray();

        char[] s3 = new char[]{'a', 'b', 'c'};
        String s4 = new String(s3);
    }
}
