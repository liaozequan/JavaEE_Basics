package forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 合并分支框架（类似分治法）
 */

class MyTask extends RecursiveTask<Integer>{
    private static final Integer VALUE = 10;
    private Integer begin;
    private Integer end;
    private Integer result;

    //创建有参构造
    public MyTask(Integer begin, Integer end) {
        this.begin = begin;
        this.end = end;
    }

    //拆分和合并的过程
    @Override
    protected Integer compute() {
        //递归判断
        if((end - begin) <= VALUE){
            //相加操作
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        }else {
            //进一步拆分
            Integer middle = (begin + end) / 2;
            //拆分左边
            MyTask myTask01 = new MyTask(begin, middle);
            //拆分右边
            MyTask myTask02 = new MyTask(middle+1, end);
            myTask01.fork();
            myTask02.fork();
            //合并结果
            result = myTask01.join() + myTask02.join();
        }
        return result;
    }
}
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建MyTask对象
        MyTask myTask = new MyTask(0, 100);
        //创建拆分合并池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        //获得合并的最终结果
        Integer result = forkJoinTask.get();
        System.out.println("计算1-100累加结果：" + result);
        //关闭池对象
        forkJoinPool.shutdown();
    }
}
