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
 * 使用future和CompletionHandler来读取文件数据
 */
public class AsyncFileChannelDemo {
    /**
     * 使用future来读取数据
     */
    @Test
    public void readAsyncFileChannel() throws IOException {
        Path path = Paths.get("E:\\idea-workspace\\JavaEE_Basics\\NIO\\src\\fileChannel.txt");
        //1.创建AsyncFileChannel
        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        //2.创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //3.调用AsyncFileChannel的read方法得到Future
        //从通道的0位置读到buffer中
        Future<Integer> future = fileChannel.read(buffer, 0);
        //4.isDone() 判断是否完成
        while (!future.isDone()){

        }
        //5.读取数据
        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
        buffer.clear();
        fileChannel.close();
    }

    /**
     * 使用CompletionHandler读取数据
     */
    @Test
    public void test() throws IOException {
        Path path = Paths.get("E:\\idea-workspace\\JavaEE_Basics\\NIO\\src\\fileChannel.txt");
        //1.创建AsyncFileChannel
        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        //2.创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        //3.调用AsyncFileChannel的read方法
        fileChannel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer buffer) {
                /**
                 * 读取数据完成后，会调用这个方法completed()
                 */
                System.out.println("result:" + result);
                buffer.flip();
                byte[] data = new byte[buffer.limit()];
                buffer.get(data);
                System.out.println(new String(data));
                buffer.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                /**
                 * 读取数据失败后，会调用这个方法failed()
                 */
            }
        });
    }
}
