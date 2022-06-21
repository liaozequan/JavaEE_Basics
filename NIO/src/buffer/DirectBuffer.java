package buffer;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接缓冲区，加快io速度
 */
public class DirectBuffer {
    @Test
    public void test() throws Exception {
        //文件输出流
        String inFile = "E:\\idea-workspace\\JavaEE_Basics\\NIO\\src\\fileChannel.txt";
        FileInputStream fis = new FileInputStream(inFile);
        FileChannel inChannel = fis.getChannel();
        //文件输入流
        String outFile = "E:\\idea-workspace\\JavaEE_Basics\\NIO\\src\\fileChannel2.txt";
        FileOutputStream fos = new FileOutputStream(outFile);
        FileChannel outChannel = fos.getChannel();
        //创建直接缓冲区
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while(true){
            buffer.clear();
            int r = inChannel.read(buffer);
            if(r == -1){
                //如果读到最后一行，退出循环
                break;
            }
            buffer.flip();
            outChannel.write(buffer);
        }
        fis.close();
        fos.close();
    }
}
