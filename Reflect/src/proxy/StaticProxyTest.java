package proxy;

/**
 * 静态代理举例
 * 特点：被代理类和代理类都写死，无法通用
 * 如果不同的被代理类（不同工厂），都希望得到代理类（代理工厂）的代理
 * 则每个被代理类都需要写一个代理类，代码重复冗余
 */
interface  ClothFactory{
    void produceCloth();
}
//代理类
class ProxyClothFactory implements ClothFactory{
    private ClothFactory factory;//用被代理类进行实例化

    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理类做的一些准备工作");
        factory.produceCloth();
        System.out.println("代理类做的一些结束工作");
    }
}

//被代理类
class NikeClothFactory implements ClothFactory{

    @Override
    public void produceCloth() {
        System.out.println("nike工厂生产服装");
    }
}
public class StaticProxyTest {
    public static void main(String[] args) {
        //实例化被代理类
        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        //实例化代理类，构造函数参数为被代理类
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nikeClothFactory);
        proxyClothFactory.produceCloth();
    }
}
