package buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

/***
 * buffer子缓冲区
 */
public class BufferSlice {
    @Test
    public void test(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for(int i=0; i<buffer.capacity(); i++){
            //往buffer中存入数据
            buffer.put((byte) i);
        }

        buffer.position(3); //子缓冲区在buffer中的起始位置
        buffer.limit(7); //子缓冲区在buffer中的末尾位置
        //创建子缓冲区:子缓冲区大小为4，在buffer中的指针为3,4,5,6
        ByteBuffer slice = buffer.slice();

        for(int i=0; i<slice.capacity(); i++){
            //修改子缓冲区的内容
            slice.put((byte) (slice.get(i)*10));
        }
        //查看buffer中的内容，由于子缓冲区被修改，因此buffer在3-6的位置也被修改
        buffer.position(0);
        buffer.limit(buffer.capacity());
        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}
