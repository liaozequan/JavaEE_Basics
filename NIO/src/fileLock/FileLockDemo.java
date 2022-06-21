package fileLock;

import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 获取文件锁的4种方法
 *  1.lock():直接对文件上锁，默认为排他锁
 *  2.lock(long position, long size, boolean shared)：
 *      position(文件中上锁的起始位置)
 *      size(文件中上锁的长度)
 *      shared(是否为共享锁)
 *  3.tryLock()
 *  4.tryLock(long position, long size, boolean shared)
 *
 *  lock()和tryLock()的区别：
 *      lock是阻塞的，如果未获得到文件锁，则阻塞当前线程
 *      tryLock是非阻塞的，如果未获得到文件锁，则返回null
 */
public class FileLockDemo {
    public static void main(String[] args) throws IOException {
        String input = "hello";
        System.out.println("input:" + input);
        ByteBuffer buffer = ByteBuffer.wrap(input.getBytes());
        String filePath = "E:\\idea-workspace\\JavaEE_Basics\\NIO\\src\\fileChannel.txt";
        Path path = Paths.get(filePath);
        //获取fileChannel的另一种方法
        //StandardOpenOption.WRITE 表示将对文件进行写操作
        //StandardOpenOption.APPEND 表示将对文件进行添加操作
        FileChannel fileChannel
                = FileChannel.open(path , StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        //将通道指针指向通道末尾
        fileChannel.position(fileChannel.size()-1);
        //给通道加锁（独占锁）,只有当前线程能读写该文件
        FileLock lock = fileChannel.lock();
        System.out.println("是否是一个共享锁：" + lock.isShared());
        //将数据写入文件
        fileChannel.write(buffer);
        fileChannel.close();




    }

}
