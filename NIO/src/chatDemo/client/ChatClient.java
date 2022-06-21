package chatDemo.client;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * 聊天室客户端
 */
public class ChatClient {
    public void startClient(String name) throws IOException {
        //1.连接服务端
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        //2.获取服务端返回的信息
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        //创建线程,接收服务端返回数据并输出
        new Thread(new ClientThread(selector)).start();
        //3.向服务端发送消息
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            //获取键盘输入的每一行数据
            String message = scanner.nextLine();
            if(message.length() > 0){
                //发送信息
                socketChannel.write(Charset.forName("UTF-8").encode(name + ":" + message));
            }
        }

    }

    @Test
    public void test1(){
        try {
            startClient("lzq");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        try {
            startClient("zhangshan");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
