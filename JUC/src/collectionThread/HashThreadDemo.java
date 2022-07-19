package collectionThread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * hashSet,hashMap线程不安全，解决方案
 *
 * new CopyOnWriteArraySet<>()
 * new ConcurrentHashMap<>()
 */
public class HashThreadDemo {
    public static void main(String[] args) {
        //HashSet<String> set = new HashSet<>();
        //解决方案
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }

        //HashMap<String, String> map = new HashMap<>();
        //解决
        //Map<String, String> map = new ConcurrentHashMap<>();
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        for (int i = 0; i < 50; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
                map.put(key, UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
