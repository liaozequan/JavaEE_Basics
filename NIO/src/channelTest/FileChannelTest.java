package channelTest;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**利用FileChannel读写文件数据到buffer中
 * position()返回channel位置，实现在特定位置读写
 * size()返回channel所关联文件大小
 * truncate(int n) 截取前n个字符，后面的字符被删去
 * force() 将channel中的数据强制写入到硬盘中
 */
public class FileChannelTest {
    /**利用FileChannel写文件
     */
    @Test
    public void testRead() throws Exception {
        //获取文件对象，RandomAccessFile(<文件路径名>, <模式，rw为读写>)
        RandomAccessFile file =
                new RandomAccessFile("E:\\idea-workspace\\JavaEE_Basics\\NIO\\src\\fileChannel.txt"
                        , "rw");
        //1.创建FileChannel
        FileChannel channel = file.getChannel();
        //2.创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);  //1024:buffer大小
        //3.从FileChannel中读取数据到buffer
        int byteReads = channel.read(buffer);
        while(byteReads != -1){ //当byteReads 等于 -1时，表示读完了最后一行
            System.out.println("读取了：" + byteReads);
            buffer.flip();  //利用flip()转换为读模式，把 limit 设为当前 position，把 position 设为 0，一般在从 Buffer 读出数据前调用
            while(buffer.hasRemaining()){   //hasRemaining(),返回1表示buffer中还有剩余内容
                System.out.println((char)buffer.get());
            }
            buffer.clear(); //将缓冲区中内容清空
            byteReads = channel.read(buffer);   //继续将FileChannel中数据读到buffer
        }
        //关闭channel
        channel.close();
        //关闭文件流
        file.close();
    }

    /**利用FileChannel写文件
     *
     */
    @Test
    public void testWrite()throws Exception {
        //获取文件对象，RandomAccessFile(<文件路径名>, <模式，rw为读写>)
        RandomAccessFile file =
                new RandomAccessFile("E:\\idea-workspace\\JavaEE_Basics\\NIO\\src\\fileChannel.txt"
                        , "rw");
        //1.创建FileChannel
        FileChannel channel = file.getChannel();
        //2.创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);  //1024:buffer大小

        String str = "new context";
        //写入之前先清空buffer
        buffer.clear();
        //写入str到buffer
        buffer.put(str.getBytes());
        buffer.flip();  //利用flip()转换为读模式
        while(buffer.hasRemaining()){   //如果buffer中还有内容
            channel.write(buffer);
        }
        channel.close();
        file.close();
    }
}
