package asyncFileChannel;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * 使用future和CompletionHandler来写入数据
 */
public class AsyncFileChannelDemo2 {
    /**
     * 使用future来写入数据
     */
    @Test
    public void writeAsyncFileChannel() throws IOException{
        Path path = Paths.get("E:\\idea-workspace\\JavaEE_Basics\\NIO\\src\\fileChannel.txt");
        //1.创建AsyncFileChannel
        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        //2.创建buffer,写入数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("test".getBytes());
        buffer.flip();
        //3.调用AsyncFileChannel的write方法得到Future
        Future<Integer> future = fileChannel.write(buffer, 0);
        //4.isDone() 判断是否完成
        while (!future.isDone()){

        }
        buffer.clear();
        System.out.println("write over");
    }

    /**
     * 使用CompletionHandler写入数据
     */
    @Test
    public void test() throws IOException{
        Path path = Paths.get("E:\\idea-workspace\\JavaEE_Basics\\NIO\\src\\fileChannel.txt");
        //1.创建AsyncFileChannel
        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        //2.创建buffer,写入数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("test".getBytes());
        buffer.flip();
        //3.调用AsyncFileChannel的write方法
        fileChannel.write(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                //写入完成会调用该方法
                System.out.println("write over, bytes written:" + result);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }
}
