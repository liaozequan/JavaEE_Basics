package buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 只读缓冲区
 * 通过原缓冲区生成只读缓冲区，结构内容与原缓冲区相同，但是只读
 * 只读缓冲区会随着原缓冲区的变化而变化
 */
public class ReadOnlyBuffer {
    @Test
    public void test(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for(int i=0; i<buffer.capacity(); i++){
            //往buffer中存入数据
            buffer.put((byte) i);
        }

        ByteBuffer readBuffer = buffer.asReadOnlyBuffer();

        for(int i=0; i<buffer.capacity(); i++){
            //修改buffer缓冲区的内容
            buffer.put(i, (byte) (buffer.get(i)*10));
        }
        //查看只读缓冲区中的内容,由于只读缓冲区和原缓冲区的指针是一致的
        //上述修改代码已经将position置于最后，因此只读缓冲区的position指针需要修改
        readBuffer.position(0);
        while(readBuffer.hasRemaining()){
            System.out.println(readBuffer.get());
        }
    }
}
