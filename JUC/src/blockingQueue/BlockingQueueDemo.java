package blockingQueue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 */
public class BlockingQueueDemo {
    /**
     * 使用add,remove存取
     */
    @Test
    public void test1() {
        //创建阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));//添加操作返回true
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.element());//返回队头
//        blockingQueue.add("d");//队列已经满，抛出异常
        System.out.println(blockingQueue.remove());//取出队头
        System.out.println(blockingQueue.remove());//取出队头
        System.out.println(blockingQueue.remove());//取出队头
//        System.out.println(blockingQueue.remove());//队已经空，抛出异常
    }

    /**
     * 使用offer,poll存取
     */
    @Test
    public void test2() {
        //创建阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));//添加操作返回true
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));//超出队列容量，但不像add会抛异常，而是返回false
        System.out.println(blockingQueue.poll());//取出队头
        System.out.println(blockingQueue.poll());//取出队头
        System.out.println(blockingQueue.poll());//取出队头
        System.out.println(blockingQueue.poll());//队已经空，但不像remove抛出异常，而是返回null
    }

    /**
     * 使用put,take存取
     */
    @Test
    public void test3() throws Exception {
        //创建阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");//添加操作，无返回值
        blockingQueue.put("b");
        blockingQueue.put("c");
//        blockingQueue.put("d");//超出队列容量，一直阻塞，直到队列有空间放入队列
        System.out.println(blockingQueue.take());//取出队头
        System.out.println(blockingQueue.take());//取出队头
        System.out.println(blockingQueue.take());//取出队头
        System.out.println(blockingQueue.take());//队已经空，一直阻塞，直到队列有数据并取出
    }

    /**
     * 使用offer(E element, long timeout, TimeUnit unit),
     *    poll(long timeout, TimeUnit unit)
     */
    @Test
    public void test4() throws Exception {
        //创建阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));//添加操作返回true
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        //超出队列容量，阻塞3秒后结束
        System.out.println(blockingQueue.offer("d", 3, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll());//取出队头
        System.out.println(blockingQueue.poll());//取出队头
        System.out.println(blockingQueue.poll());//取出队头
        //队已经空，阻塞3秒后结束
        System.out.println(blockingQueue.poll(3, TimeUnit.SECONDS));
    }
}
