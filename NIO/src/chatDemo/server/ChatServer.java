package chatDemo.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Set;

/**
 * 聊天室服务端
 */
public class ChatServer {
    /**
     * 服务端启动方法
     */
    public static void startServer() throws IOException {
        //1.创建selector选择器
        Selector selector = Selector.open();
        //2.创建serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3.为channel绑定监听端口
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        //4把channel注册到selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功");
        //5.循环检查，等待新连接接入
        for(;;){
            //获取channel数量
            int readChannels = selector.select();
            if(readChannels == 0){
                //如果没有获取到channel，则重新循环
                continue;
            }
            //获取可用的channel
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                //取出后，删除已取出的key,如果不删除，则将进入下一次循环，而下一次中的channel可能是空的，会报错
                iterator.remove();
                //6.根据状态，调用对应方法实现具体业务方法
                //6.1如果是accept状态
                if(key.isAcceptable()){
                    acceptOperator(serverSocketChannel, selector);
                }else if (key.isConnectable()){

                }else if (key.isReadable()){
                //6.1如果是可读状态
                    readOperator(selector, key);
                }else if (key.isWritable()){

                }else {

                }

            }
        }

    }




    //处理accept状态
    private static void acceptOperator(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        //1.创建socketChannel
        SocketChannel socketChannel = serverSocketChannel.accept();
        //2.非阻塞模式
        socketChannel.configureBlocking(false);
        //3.把channel注册到selector中，监听可读状态(客户端连接)
        socketChannel.register(selector, SelectionKey.OP_READ);
        //4.回复客户端信息
        socketChannel.write(Charset.forName("UTF-8").encode("欢迎连接聊天室"));
    }
    //处理read状态
    private static void readOperator(Selector selector, SelectionKey key) throws IOException {
        //1.从key中获取已经在就绪状态的通道
        SocketChannel socketChannel = (SocketChannel) key.channel();
        //2.创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //3.循环读取客户端信息
        int readlength = socketChannel.read(buffer);
        String message = "";
        if (readlength > 0){
            buffer.flip();
            message += Charset.forName("UTF-8").decode(buffer);
        }
        //4.将channel再次注册到selector上，继续监听可读状态(保证再监听到该客户端继续发的信息)
        socketChannel.register(selector, SelectionKey.OP_READ);
        //5.把客户端发来的信息，广播到其他客户端
        if(message.length() > 0){
            System.out.println(message);
            castOtherClient(message, selector, socketChannel);
        }
    }

    private static void castOtherClient(String message, Selector selector, SocketChannel socketChannel) throws IOException {
        //1.获取所有已经接入的客户端channel
        Set<SelectionKey> selectionKeySet = selector.keys();
        //2.循环向其他所有客户端发送信息（除发送信息的客户端）
        for(SelectionKey key : selectionKeySet){
            //获取每个通道
            Channel targetChannel = key.channel();
            /**要发送信息的Channel必须是已经连接的channel，类型为SocketChannel
             * 且排除发送信息的channel
             */
            if(targetChannel instanceof SocketChannel && targetChannel != socketChannel){
                ((SocketChannel)targetChannel).write(Charset.forName("UTF-8").encode(message));
            }
        }
    }

    public static void main(String[] args) {
        try {
            startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
