package buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**buffer三个属性
 * capacity：buffer总容量
 * position：
 *  （1）buffer写入数据时：position表示写入的位置，初始为0，最大为capacity-1
 *  （2）从buffer读数据时：position表示读的位置，可以通过flip()方法将position置0，从头读
 *      如果在读之前，position=2，则表示从第3个开始读
 * limit:
 *  （1）buffer写入数据时：limit表示还可以写多少个数据
 *  （2）从buffer读数据时：limit表示还可以读多少个数据
 *
 */
public class BufferProperty {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.compact();
        buffer.rewind();
    }
}
