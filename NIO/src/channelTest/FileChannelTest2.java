package channelTest;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**实现通道之间的数据传输,实现文件复制
 * transferFrom
 * transferTo
 * 上面两个方法功能一样，只是调用者和实参位置不同
 */
public class FileChannelTest2 {
    /**
     * transferFrom测试
     */
    @Test
    public void test1() throws Exception {
        RandomAccessFile file1 =
                new RandomAccessFile("E:\\idea-workspace\\JavaEE_Basics\\NIO\\src\\fileChannel.txt"
                        , "rw");
        FileChannel fromChannel = file1.getChannel();

        RandomAccessFile file2 =
                new RandomAccessFile("E:\\idea-workspace\\JavaEE_Basics\\NIO\\src\\fileChannel2.txt"
                        , "rw");
        FileChannel toChannel = file2.getChannel();
        //fromChannel数据传输到ToChannel中
        long position = 0;
        long size = fromChannel.size();
        //接收channel.transferFrom(发送channel, 发送内容起始位置, 发送内容终止位置);
        toChannel.transferFrom(fromChannel, position, size);
        fromChannel.close();
        toChannel.close();
        file1.close();
        file2.close();
    }

    /**
     * transferTo测试
     */
    public void test2() throws Exception {
        RandomAccessFile file1 =
                new RandomAccessFile("E:\\idea-workspace\\JavaEE_Basics\\NIO\\src\\fileChannel.txt"
                        , "rw");
        FileChannel fromChannel = file1.getChannel();

        RandomAccessFile file2 =
                new RandomAccessFile("E:\\idea-workspace\\JavaEE_Basics\\NIO\\src\\fileChannel2.txt"
                        , "rw");
        FileChannel toChannel = file2.getChannel();
        //fromChannel数据传输到ToChannel中
        long position = 0;
        long size = fromChannel.size();
        //发送channel.transferTo(发送内容起始位置, 发送内容终止位置, 接收channel);
        fromChannel.transferTo(position, size, toChannel);
        fromChannel.close();
        toChannel.close();
        file1.close();
        file2.close();
    }
}
