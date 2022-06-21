package chatDemo.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * 创建线程,接收服务端返回数据并输出
 */
public class ClientThread implements Runnable{
    private Selector selector;

    public ClientThread(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
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
                    }else if (key.isConnectable()){
                    }else if (key.isReadable()){
                        //6.1如果是可读状态
                        readOperator(selector, key);
                    }else if (key.isWritable()){

                    }else {

                    }

                }
            }
        }catch (Exception e){

        }

    }

    private void readOperator(Selector selector, SelectionKey key) throws IOException {
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
        //5.输出服务端发来的信息
        if(message.length() > 0){
            System.out.println(message);
        }
    }
}
