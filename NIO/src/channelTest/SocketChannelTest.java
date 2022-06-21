package channelTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**SocketChannel是连接TCP网络套接字的通道
 * 主要处理网络IO通道，基于TCP连接传输
 *
 */
public class SocketChannelTest {
    public static void main(String[] args) throws Exception {
        //创建SocketChannel方法一：
        SocketChannel sc = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));

        //创建SocketChannel方法二：
//        SocketChannel sc1 = SocketChannel.open();
//        sc1.connect(new InetSocketAddress("www.baidu.com", 80));
        /**
         * 连接检验方法
         */
        sc.isOpen();    //测试SocketChannel是否为open状态
        sc.isConnected();   //测试SocketChannel是否已被连接
        sc.isConnectionPending();   //测试SocketChannel是否正在连接
        sc.finishConnect(); //测试正在进行套接字连接的SocketChannel是否完成连接
        //设置非阻塞模式
        sc.configureBlocking(false);
        //读操作，先写入buffer在读出
        ByteBuffer buffer = ByteBuffer.allocate(16);
        sc.read(buffer);
        sc.close();
        //如果是阻塞模式，则未完成读入前无法执行下面代码
        System.out.println("read over");
    }
}
