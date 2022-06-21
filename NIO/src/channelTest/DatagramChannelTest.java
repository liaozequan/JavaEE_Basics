package channelTest;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**DatagramChannel实现无连接协议(UDP/IP)。
 * 无连接：对特定IP地址进行read和write操作
 *
 *
 */
public class DatagramChannelTest {
    /**
     * send()发送的实现
     */
    @Test
    public void sendDatagram() throws Exception {
        //打开DatagramChannel
        DatagramChannel sendChannel = DatagramChannel.open();
        //声明发送的ip地址
        InetSocketAddress sendAddress = new InetSocketAddress("127.0.0.1", 9999);
        //发送
        while (true){
            ByteBuffer buffer = ByteBuffer.wrap("hello".getBytes(StandardCharsets.UTF_8));
            sendChannel.send(buffer, sendAddress);
            System.out.println("发送完成");
            Thread.sleep(2000);
        }
    }

    /**
     * receive()接收的实现
     */
    @Test
    public void receiveDatagram() throws IOException {
        DatagramChannel receiveChannel = DatagramChannel.open();
        InetSocketAddress receiveAddress = new InetSocketAddress(9999);
        //绑定端口号
        receiveChannel.bind(receiveAddress);
        //buffer
        ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
        //接收
        while (true){
            receiveBuffer.clear();//buffer写入前对指针的操作
            //socketAddress为发送方地址
            SocketAddress socketAddress = receiveChannel.receive(receiveBuffer);
            receiveBuffer.flip();//buffer读出前对指针的操作
            System.out.println("发送方地址:"+socketAddress.toString());
            System.out.println("接收内容"+Charset.forName("UTF-8").decode(receiveBuffer));
        }
    }

    /**
     * 向特定ip地址使用read和write接收和发送数据
     */
    @Test
    public void connTest() throws IOException {
        DatagramChannel connChannel = DatagramChannel.open();
        //绑定端口号
        connChannel.bind(new InetSocketAddress(8998));
        //必须连接后才能调用read，write
        connChannel.connect(new InetSocketAddress("127.0.0.1", 8998));
        //write方法
        connChannel.write(ByteBuffer.wrap("hello".getBytes(StandardCharsets.UTF_8)));
        //buffer
        ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
        //read方法
        receiveBuffer.clear();
        connChannel.read(receiveBuffer);
        receiveBuffer.flip();
        System.out.println("接收内容"+Charset.forName("UTF-8").decode(receiveBuffer));
        connChannel.close();
    }
}
