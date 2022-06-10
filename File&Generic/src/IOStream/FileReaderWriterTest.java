package IOStream;

import org.junit.Test;

import java.io.*;

/**节点流
 * 字符输入流FileReader
 * 字符输出流FileWriter
 */
public class FileReaderWriterTest {
    /**读取文件内容，输出到控制台
     * 不建议抛出异常，因为如果在流关闭之前出现异常，程序就直接停止，流就无法关闭
     */
    @Test
    public void testRead1(){
        FileReader fileReader = null;
        try {
            //1.实例化操作对象，指明要操作的文件
            File file = new File("hello.txt");//相对路径：当前module下
            //2.提供具体的流
            fileReader = new FileReader(file);
            //3.数据的读入
            //read()返回读入的一个字符（int型ASCII），若到达文件末尾，则返回-1
            int data = fileReader.read();
            while(data!=-1){
                System.out.print((char)data);
                //读取下一个字符
                data = fileReader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //4.关闭流
                if(fileReader != null){
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读入数据FileReader优化
     */
    @Test
    public void testRead2() {
        FileReader fileReader = null;
        try {
            //1.实例化操作对象，指明要操作的文件
            File file = new File("hello.txt");//相对路径：当前module下
            //2.提供具体的流
            fileReader = new FileReader(file);
            //3.数据的读入
            /**fileReader.read(cbuf)返回每次读入到cbuf数组的长度，
            * 如果最后一次读入不够cbuf长度，则返回剩余字符长度
             * 若到达文件末尾，则返回-1
            */
            char[] cbuf = new char[5];
            int len;
            while ((len=fileReader.read(cbuf))!=-1){
                System.out.print("len="+len+"---");
//                for(int i=0; i<cbuf.length; i++){     错误写法
//                    System.out.print(cbuf[i]);
//                }
                for(int i=0; i<len; i++){     //正确写法
                  System.out.print(cbuf[i]);
                }
                //方法2
                String s = new String(cbuf, 0, len);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fileReader != null)
                    fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 输出文件流
     * 若文件不存在，则自动创建
     */
    @Test
    public void writeTest(){
        FileWriter fileWriter = null;
        try {
            //1.实例化操作对象，指明要操作的文件
            File file = new File("hello1.txt");//相对路径：当前module下
            //2.提供具体的输出流
            //FileWriter(file, append); append:true则在原有文件添加，false：直接覆盖原文件
            fileWriter = new FileWriter(file, true);
            //3.输出操作
            fileWriter.write("hello\n");
            fileWriter.write("world");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fileWriter!=null)
                    fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 输入流输出流结合，实现复制文件
     */
    @Test
    public void copy(){
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            File srcFile = new File("hello.txt");
            File destFile = new File("hello2.txt");
            fileReader = new FileReader(srcFile);
            fileWriter = new FileWriter(destFile);
            char[] cbuf = new char[5];
            int len;
            while ((len=fileReader.read(cbuf))!=-1){
                //每次写出len个字符
                fileWriter.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
