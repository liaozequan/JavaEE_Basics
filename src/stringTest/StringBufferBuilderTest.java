package stringTest;

/**
 * string,stringBuffer,stringBuilder三者区别
 * 1.string：不可变的字符序列，
 * 2.stringBuffer：可变，线程安全，效率低，方法都有synchronized
 * 3.stringBuilder：可变，线程不安全，效率高
 *
 * string,stringBuffer,stringBuilder三者共同点
 * 1.都是char[] value = new char[]
 *
 * StringBuffer/StringBuffer初始开辟数组长度16，如果字符串长度超过16，如何扩容
 * 默认情况下，扩容为原来容量的2倍+2，同时将原数组数据拷贝到新数组中
 * 建议声明时使用new StringBuffer(int capacity)或new StringBuilder(int capacity)避免经常扩容导致效率下降
 */
public class StringBufferBuilderTest {
    public static void main(String[] args) {
        String s1 = new String();             //new char[0]
        String s2 = new String("abc");//new char[]{'a','b','c'}

        StringBuffer sb1 = new StringBuffer();//char[] value = new char[16]
        sb1.append('a');//value[0]='a'

        StringBuffer sb2 = new StringBuffer("abc");//char[] value = new char["abc".length()+16]

    }
}
