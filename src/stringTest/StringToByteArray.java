package stringTest;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/***
 * 编码：String-->byte[]      getBytes()
 * 解码：byte[]-->String      string构造器
 */
public class StringToByteArray {
    public static void main(String[] args) {
        String s1 = "abc123中国";
        byte[] b1 = s1.getBytes();
        //默认字符集utf-8
        System.out.println(Arrays.toString(b1));


        try {
            //使用字符集gbk，转换为ascii
            byte[] b2 = s1.getBytes("gbk");
            System.out.println(Arrays.toString(b2));
            String s3 = new String(b2);
            System.out.println(s3); //使用gbk编码，再使用utf-8解码，导致中文乱码
            String s4 = new String(b2, "gbk");
            System.out.println(s4);//使用gbk编码，再使用gbk解码，正常输出abc123中国
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String s2 = new String(b1);
        System.out.println(s2);//正常输出abc123中国
    }
}
