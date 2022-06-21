package pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Pipe管道实例
 */
public class PipeDemo {
    public static void main(String[] args) throws IOException {
        //1.获取管道
        Pipe pipe = Pipe.open();
        //2.获取sink通道，存储数据
        Pipe.SinkChannel sinkChannel = pipe.sink();
        //3.创建缓冲区，往缓冲区写入数据
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        writeBuffer.put("hello world".getBytes());
        writeBuffer.flip();
        //4.将buffer写入sinkChannel
        sinkChannel.write(writeBuffer);
        //5.获取source通道
        Pipe.SourceChannel sourceChannel = pipe.source();
        //6.创建缓冲区，读取数据
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int length = sourceChannel.read(readBuffer);
        System.out.println(new String(readBuffer.array(), 0, length));

        //7.关闭相关通道
        sourceChannel.close();
        sinkChannel.close();
    }
}
