package webSocket;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP网络编程
 */
public class UDPTest {
    @Test
    public void sender(){
        DatagramSocket socket = null;
        try {
            //UDPsocket
            socket = new DatagramSocket();
            //发送的数据
            String str = "发送UDP数据！";
            byte[] data = str.getBytes();
            //接收方ip
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            //UDP数据包(数据，数据起始指针，数据末尾指针，目标ip,端口号)
            DatagramPacket packet = new DatagramPacket(data, 0, data.length, inetAddress, 9091);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket != null)
                socket.close();
        }

    }

    @Test
    public void receiver(){
        DatagramSocket socket = null;
        try {
            //接收端UDPsocket，需指定接收端的端口号
            socket = new DatagramSocket(9091);
            //指定接收数据的缓冲区buffer
            byte[] buffer = new byte[100];
            //提供一个UDP数据包来接收数据(接收数据使用的缓冲区，数据起始指针，数据末尾指针)
            DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
            //将接收的数据放入数据包中
            socket.receive(packet);
            //从数据包中输出数据
            System.out.println(new String(packet.getData(), 0, packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(socket != null)
                    socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
