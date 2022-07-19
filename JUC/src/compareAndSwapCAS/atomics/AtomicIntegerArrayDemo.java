package compareAndSwapCAS.atomics;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**AtomicIntegerArray测试
 *
 */
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray array1 = new AtomicIntegerArray(5);
        AtomicIntegerArray array2 = new AtomicIntegerArray(new int[5]);
        AtomicIntegerArray array3 = new AtomicIntegerArray(new int[]{1,2,3,4,5});
        System.out.println("array1");
        for (int i = 0; i < array1.length(); i++) {
            System.out.print(array1.get(i)+" ");
        }
        System.out.println();
        System.out.println("array2");
        for (int i = 0; i < array2.length(); i++) {
            System.out.print(array2.get(i)+" ");
        }
        System.out.println();
        System.out.println("array3");
        for (int i = 0; i < array3.length(); i++) {
            System.out.print(array3.get(i)+" ");
        }
        System.out.println();
        int temp = 0;
        //返回数组下标0的值，并将99存入数组下标0的位置
        temp = array3.getAndSet(0, 99);
        System.out.println("array3[0]原值:" + temp + " array3[0]当前值：" + array3.get(0));

        //返回数组下标0的值，并+1存入数组下标0的位置
        temp = array3.getAndIncrement(0);
        System.out.println("array3[0]原值:" + temp + " array3[0]当前值：" + array3.get(0));
    }
}
