package webSocket;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP协议测试：1.客户端发送信息给服务端，服务端将数据显示
 *            2.服务端返回“发送成功”提升给客户端，客户端接受服务端信息并显示
 */
public class TCPTest {
    @Test
    public void client(){
        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            //实例化服务端ip
            InetAddress serverIP = InetAddress.getByName("127.0.0.1");
            //实例化socket=ip+端口号
            socket = new Socket(serverIP, 8851);
            //实例化输出流
            os = socket.getOutputStream();
            os.write("I AM CLIENT!".getBytes());
            //关闭客户端数据流的输出，让服务端可以停止接收才能执行下面的代码
            socket.shutdownOutput();
            //从服务端接受 确认接受的返回信息
            is = socket.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[5];
            int len;
            while((len = is.read(buffer)) != -1){
                baos.write(buffer, 0, len);
            }
            //从baos中的buf数组中输出数据
            System.out.println(baos.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(os != null)
                    //关闭输出流
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(socket != null)
                    //关闭socket流
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }

    @Test
    public void server(){
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        OutputStream os = null;
        try {
            //实例化服务器端的ServerSocket，因为是接收方，只需要指明端口号
            ss = new ServerSocket(8851);
            //从ServerSocket中获取来自于客户端socket
            socket = ss.accept();
            //实例化输入流
            is = socket.getInputStream();
            //因为接收的是字节流，而如果传输过来的是字符流，因此使用baos避免乱码
            //baos不需要指定输出的文件，因为数据会写入baos对象里的buf数组中
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[5];
            int len;
            while((len = is.read(buffer)) != -1){
                baos.write(buffer, 0, len);
            }
            //从baos中的buf数组中输出数据
            System.out.println(baos.toString());
            //输出发送方ip地址
            System.out.println("收到来自于："+socket.getInetAddress()+"的数据");
            //返回给客户端 确认接收信息
            os = socket.getOutputStream();
            os.write("发送成功！".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**关闭5个流
             */
            try {
                if(os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(ss != null)
                    ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
