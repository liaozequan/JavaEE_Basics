package selector;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * selector实现客户端/服务端操作
 */
public class SelectorCSDemo {
    /**
     * 客户端
     */
    @Test
    public void client() throws Exception {
        //1.获取通道，绑定服务端主机和端口号
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        //2.通道设定为非阻塞状态(selector硬性要求)
        socketChannel.configureBlocking(false);
        //3.创建buffer
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        //4.往buffer中写入数据
        buffer.put(new Date().toString().getBytes());
        //5.buffer写入通道
        buffer.flip();
        socketChannel.write(buffer);
        //6.关闭
        socketChannel.close();
    }

    /**
     * 服务端
     */
    @Test
    public void server() throws Exception {
        //1.获取通道，绑定服务端主机和端口号
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2.通道设定为非阻塞状态(selector硬性要求)
        serverSocketChannel.configureBlocking(false);
        //3.创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //4.绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //5.获取selector选择器
        Selector selector = Selector.open();
        //6.通道注册到selector上，并监听
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //7.选择器进行轮询，查找通道的就绪操作，并进行后续操作
        while(selector.select()>0){
            //如果通道的指定操作处于就绪状态
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if(key.isAcceptable()){
                    //如果监听的通道处于可连接状态
                    //获取连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //切换为非阻塞
                    socketChannel.configureBlocking(false);
                    //将该通道注册到selector中
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }else if (key.isConnectable()){

                }else if (key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    //读取数据
                    int length = 0;
                    while ((length = socketChannel.read(buffer))>0){
                        buffer.flip();
                        //注意：buffer.array()中的buffer不能是allocateDirect()实例化出来的
                        System.out.println("received:" + new String(buffer.array(), 0, length));
                        buffer.clear();
                    }
                }else if (key.isWritable()){

                }else {

                }
            }
            iterator.remove();

        }
    }
}
