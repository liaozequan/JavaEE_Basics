package IOStream;

import org.junit.Test;

import java.io.*;

/**
 * 处理流之二：转换流（字符流）
 * InputStreamReader：将一个字节输入流转换为字符的输入流(字节-->字符)
 * OutputStreamWriter：将一个字符的输出流转换为字节的输出流(字符-->字节)
 * 作用：提供字符流和字节流之间的转换
 */
public class IOStreamReaderWriterTest {
    @Test
    public void test(){
        InputStreamReader isr = null;
        try {
            FileInputStream fis = new FileInputStream("helloGBK.txt");
            //指定"hello.txt"文件的编码，这个编码取决于文件存储时使用的编码
            isr = new InputStreamReader(fis, "GBK");
            char[] cbuf = new char[5];
            int len;
            while ((len=isr.read(cbuf))!=-1){
                System.out.println(new String(cbuf, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(isr != null)
                    isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void copyToGBK(){
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            FileInputStream fis = new FileInputStream("hello.txt");
            FileOutputStream fos = new FileOutputStream("helloGBK.txt");
            //指定"hello.txt"文件的编码，这个编码取决于文件存储时使用的编码
            isr = new InputStreamReader(fis, "UTF-8");
            osw = new OutputStreamWriter(fos, "GBK");
            char[] cbuf = new char[5];
            int len;
            while ((len=isr.read(cbuf))!=-1){
                osw.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(isr != null)
                    isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(osw != null)
                    osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
