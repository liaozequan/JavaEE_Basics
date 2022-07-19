package volatileDemo;

/**
 * volatile变量有序性测试
 */
public class VolatileReSortDemo {
    int i = 0;
    volatile boolean flag = false;

    /**
     * volatile有序性保证了read()方法输出的i一定为2，不会为0
     * 如果重排序，可能flag=true在i=2之前，则read()中的if判断通过，直接输出i=0
     */
    public void write(){
        i = 2;
        //StoreStore屏障：禁止上面普通写于下面的volatile写重排序
        flag = true;//对volatile变量写
        //StoreLoad屏障：禁止上面的volatile写和下面可能出现的volatile读/写重排序
    }
    public void read(){
        if(flag){//对volatile变量读
            //LoadLoad屏障：禁止处理器吧上面的volatile读操作与下面的普通读重排序
            //LoadStore屏障：禁止处理器吧上面的volatile读操作与下面的普通写重排序
            System.out.println(i);
        }
    }
}
