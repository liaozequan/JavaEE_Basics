package collectionThread;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * list集合线程不安全问题，3个解决方案

* 解决方案1：使用vector，vector是线程安全的
* add()有synchronized关键字
* 较少使用
*/

/**解决方案2：使用Collections.synchronizedList()
 * 返回是一个List
 */

/**解决方案3：使用CopyOnWriteArrayList<>() 写时复制机制
 * 当多个进程读一个list时，共享读
 * 当一个进程写入时，会复制这个list，在新的list中写入，此时在读的进程读的是原理的list，暂时不是写入操作影响
 * 当这个写入进程写完后，会将新的list与旧list合并，所有读进程读新的list
 */

public class ListThreadDemo {
    public static void main(String[] args) {
        //ArrayList<String> list = new ArrayList();
        //解决方案1：Vector<String> list = new Vector<>();
        //解决方案2：List<String> list = Collections.synchronizedList(new ArrayList<>());
        //解决方案3：
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        /**
         * 运行后出现.ConcurrentModificationException（并发修改异常）
         * 因为add()方法不支持多线程，在输出时出现并发异常，因此list是线程不安全的
         */
    }
}
