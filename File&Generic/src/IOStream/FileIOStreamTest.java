package IOStream;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**节点流
 * 字节输入流FileInputStream
 * 字节输出流FileOutputStream
 */
public class FileIOStreamTest {
    /**
     * 用字节流处理字符数据文件，但可能出现乱码
     */
    @Test
    public void testFileInputStream(){
        FileInputStream fis = null;
        try {
            File file = new File("hello.txt");
            fis = new FileInputStream(file);
            byte[] buffer = new byte[5];
            int len;//每次读取字节数
            while((len=fis.read(buffer))!=-1){
                String s = new String(buffer, 0, len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis!=null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 字节IO流处理图片文件，实现copy
     */
    @Test
    public void testFileInputStream1(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File file1 = new File("测试效果.png");
            File file2 = new File("测试效果copy.png");
            fis = new FileInputStream(file1);
            fos = new FileOutputStream(file2);
            byte[] buffer = new byte[100];
            int len;//每次读取字节数
            while((len = fis.read(buffer))!=-1){
                fos.write(buffer,0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis!=null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fos!=null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
