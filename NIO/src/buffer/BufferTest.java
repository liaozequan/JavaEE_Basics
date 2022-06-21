package buffer;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * buffer基础练习
 */
public class BufferTest {
    @Test
    public void test1() throws Exception {
        IntBuffer intBuffer = IntBuffer.allocate(8);
        //往IntBuffer中存入数据
        for(int i=0; i<intBuffer.capacity(); i++){
            int temp = i*2;
            intBuffer.put(temp);
        }
        //读出前重置指针
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            int value = intBuffer.get();
            System.out.println(value);
        }
    }
}
