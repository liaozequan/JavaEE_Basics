package IOStream;

import org.junit.Test;

import java.io.*;

/**处理流之一：缓冲流Buffered
 * 字节输入流BufferedInputStream
 * 字节输出流BufferedOutputStream
 * 字符输入流BufferedReader
 * 字符输出流BufferedWriter
 * 1.作用：提升读写速度
 *      提速原因：内部提供缓冲区
 */
public class BufferedTest {
    @Test
    public void BufferedStreamTest(){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //1.造文件
            File file1 = new File("测试效果.png");
            File file2 = new File("测试效果buffercopy.png");
            //2.造流
            //2.1造节点流
            FileInputStream fis = new FileInputStream(file1);
            FileOutputStream fos = new FileOutputStream(file2);
            //2.2造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            //3复制细节，读取，写入
            byte[] buffer = new byte[10];
            int len;
            while((len = bis.read(buffer)) != -1){
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭4个流，先关闭外层处理流，再关闭内层节点流
            //关闭外层流时，内层流也会关闭，因此可以省略关闭内层流
            try {
                if(bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    public void bufferedReadWriteTest(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader("hello.txt"));
            bw = new BufferedWriter(new FileWriter("helloBuffer.txt"));
            String data;
            while ((data=br.readLine())!=null){    //readLine一次读一行,结束返回null
                //每次写出len个字符
                bw.write(data);
                bw.newLine();//换行
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
