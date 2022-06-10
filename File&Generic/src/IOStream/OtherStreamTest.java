package IOStream;

import org.junit.Test;

import java.io.*;

/**其他流的使用
 * 1.标准输入输出流
 * 2.打印流
 * 3.数据流
 */
public class OtherStreamTest {
    /**1.标准输入输出流
     * system.in:标准输入流，默认从键盘输入
     * System.out标准输出流，默认从控制台输出
     * 输入字符串，输出大写的字符串，当输入“exit”时退出
     */
    @Test
    public void test(){
        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            while(true){
                System.out.println("请输入字符串");
                String data = br.readLine();
                if("exit".equalsIgnoreCase(data)){
                    break;
                }
                System.out.println(data.toUpperCase());
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
        }
    }

    /**2.打印流
     * 将数据输出为一个文件保存在硬盘
     */
    @Test
    public void test1(){
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File("ASCII.txt"));
            //创建打印流，设置为自动刷新模式（当换行时，会刷新输出缓冲区）
            ps = new PrintStream(fos, true);
            if(ps != null){
                //将输出流目的地从控制台转换为文件
                System.setOut(ps);
            }
            for(int i=0; i<=255; i++){
                System.out.println((char)i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ps != null)
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**3.数据流
     *DataOutputStream：数据输出流，存储基本数据类型和String，不要穿插写入不同数据类型数据
     * DataInputStream：数据输入流，读取基本数据类型和String
     */
    @Test
    public void testDataOutputStream(){
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream("dataStream.txt"));
            dos.writeUTF("abc");
            dos.writeUTF("bcd");
            dos.writeUTF("zzz");
            dos.writeInt(123);
            //如果中间写入了其他类型数据，这“zzz”就无法写入

            dos.writeInt(456);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(dos != null)
                    dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testDataInputStream(){
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream("dataStream.txt"));
            String s = dis.readUTF();
            System.out.println(s);
            String s1 = dis.readUTF();
            System.out.println(s1);
            String s2 = dis.readUTF();
            System.out.println(s2);
            int s3 = dis.readInt();
            System.out.println(s3);
            int s4 = dis.readInt();
            System.out.println(s4);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(dis != null)
                    dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
