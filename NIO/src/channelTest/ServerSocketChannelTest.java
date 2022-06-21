package channelTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**ServerSocketChannel本身不传输数据，只是一个socket监听器
 *ServerSocketChannel没有bind()，需要socket来绑定
 */
public class ServerSocketChannelTest {
    /**利用ServerSocketChannel来监听socket，并传输buffer中数据
     *测试方法：访问 http://127.0.0.1:8888/
     */
    public static void main(String[] args) throws Exception {
        //端口号
        int port = 8888;
        //buffer,在缓冲区中写入"hello"
        ByteBuffer buffer = ByteBuffer.wrap("hello".getBytes());
        //ServerSocketChannel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //绑定端口
        ssc.socket().bind(new InetSocketAddress(port));
        //设置非阻塞模式
        ssc.configureBlocking(false);
        //accept()监听是否有新的连接传入
        while(true){
            SocketChannel sc = ssc.accept();
            //如果是阻塞模式，则在ssc.accept()方法就阻塞，就不会提示无连接
            if(sc == null){
                //无新连接传入
                System.out.println("waiting for connection!");
                Thread.sleep(2000);
            }else {
                //有新连接传入
                System.out.println("Incoming new connection from:" + sc.socket().getRemoteSocketAddress());
                buffer.rewind();//缓存指针指向0(开头)，用于从buffer中读数据
                sc.write(buffer);
                sc.close();
            }
        }
    }
}
