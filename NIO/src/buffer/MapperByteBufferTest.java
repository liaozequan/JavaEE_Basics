package buffer;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 内存映射buffer
 * io速度比流或通道的方式快
 */
public class MapperByteBufferTest {
    public static int start = 0;
    public static int size = 1024;
    public static void main(String[] args) throws Exception {
        //获取文件对象，RandomAccessFile(<文件路径名>, <模式，rw为读写>)
        RandomAccessFile file =
                new RandomAccessFile("E:\\idea-workspace\\JavaEE_Basics\\NIO\\src\\fileChannel.txt"
                        , "rw");
        //1.创建FileChannel
        FileChannel channel = file.getChannel();
        MappedByteBuffer mbb = channel.map(FileChannel.MapMode.READ_WRITE, start, size);
        mbb.put(0, (byte) 97);
        mbb.put(1023, (byte) 101);
        file.close();
    }

}
