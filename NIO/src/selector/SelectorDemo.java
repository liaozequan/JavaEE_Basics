package selector;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * selector的创建
 * selector管理的通道必须上非阻塞的通道
 */
public class SelectorDemo {
    @Test
    public void test() throws Exception {
        //创建selector
        Selector selector = Selector.open();
        //创建通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //将通道设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //查看通道支持哪些操作
        System.out.println(serverSocketChannel.validOps());
        //将通道注册到selector
        //第一个参数：serverSocketChannel注册到哪个selector上
        //第二个参数：selector关心serverSocketChannel的哪些事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //查询已经就绪的通道操作
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        //遍历selectionKeys
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while(iterator.hasNext()){
            SelectionKey key = iterator.next();
            if(key.isAcceptable()){

            }else if (key.isConnectable()){

            }else if (key.isReadable()){

            }else if (key.isWritable()){

            }else {

            }
            iterator.remove();
        }
    }
}
