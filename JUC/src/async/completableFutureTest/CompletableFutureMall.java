package async.completableFutureTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**案例说明：各电商比价需求
 * 1.需求：
 *  1.1 同一商品，同时搜索出各大电商平台的价格
 *  1.2 同一商品，同时搜索出一个平台不同店家的价格
 *
 * 2.技术栈：函数式编程、链式编程、stream流式计算
 *
 */
public class CompletableFutureMall {
    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao")
    );

    /**不使用多线程，一个个平台依次查询
     * 流式编程：List<NetMall>--->映射---->List<String>
     * @param list 电商平台列表
     * @param productName 要查询的商品
     * @return 返回各平台该商品价格
     */
    public static List<String> getPrice(List<NetMall> list, String productName){
        return list.stream().map(netMall ->
                String.format("《" + productName + "》" + "in %s price is %.2f",
                        netMall.getMallName(),
                        netMall.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    /**使用CompletableFuture异步处理，3个平台异步查询价格
     * 流式编程：List<NetMall>--->List<CompletableFuture<String>>---->List<String>
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName){
        return list.stream().map(netMall ->
                //把netMall（三个电商平台）都映射为一个异步任务（CompletableFuture<String>）
                CompletableFuture.supplyAsync(()->
                        String.format("《" + productName + "》" + "in %s price is %.2f",
                        netMall.getMallName(),
                        netMall.calcPrice(productName))))
                //把多个异步任务加入到list中
                .collect(Collectors.toList())
                //将每个异步任务的返回值映射为<String>存入List<String>
                .stream().map(s->s.join())
                .collect(Collectors.toList());
    }
    public static void main(String[] args) {
        List<String> mysql = getPriceByCompletableFuture(list, "mysql");
        for(String s : mysql){
            System.out.println(s);
        }
    }
}

class NetMall{
    //电商网站名称
    private String mallName;

    /** 模拟计算价格，延迟1s
     * @param productName 商品名
     * @return 返回随机商品价格
     */
    public double calcPrice(String productName){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }

    public NetMall(String mallName) {
        this.mallName = mallName;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }
}
