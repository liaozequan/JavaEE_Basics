package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Human{
    String getName();
    void eat(String food);
}
//被代理类
class SuperMan implements Human{

    @Override
    public String getName() {
        return "I am superman";
    }

    @Override
    public void eat(String food) {
        System.out.println("I eat "+food);
    }
}

/**要实现动态代理，需要解决的问题？
 * 1.如何根据被加载到内存中的被代理类，动态地创建代理类及其对象？
 * 2.当通过代理类的对象调用方法时，如何动态地去调用被代理类同名的方法？
 */

class ProxyFactory{
    //此方法通过输入一个被代理类返回一个代理类的对象，解决问题一
    public static Object getProxyInstance(Object obj){//obj:被代理类的对象
        MyInvocationHandler handler = new MyInvocationHandler();
        //为handler对象赋值被代理类
        handler.setObj(obj);
        /**
         * 参数一：被代理类的类加载器
         * 参数二：被代理类所实现的接口
         * 参数三：自定义InvocationHandler，解决问题二
         * return：返回的是代理类对象，因为代理类和被代理类都实现同一个接口，因此代理类对象类型就是被代理类实现的接口
         * （即obj.getClass().getInterfaces()）
         */
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }
}
class MyInvocationHandler implements InvocationHandler{
    private Object obj;//使用被代理类对象赋值
    //设置被代理类方法
    public void setObj(Object obj) {
        this.obj = obj;
    }

    /**当通过调用代理类的对象，调用a方法时，就会自动调用如下方法
     * 将被代理类要执行的a方法声明在invoke方法中
     * 解决问题二
     * @param proxy 代理类
     * @param method method即为代理类对象调用的方法，此方法也就是被代理类对象要调用的方法
     * @param args  被代理类方法的实参列表
     * @return  返回被代理类方法执行后的返回值
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method即为代理类对象调用的方法，此方法也就是被代理类对象要调用的方法
        System.out.println("代理类做的准备工作");
        Object reutrnVal = method.invoke(obj, args);
        System.out.println("代理类做的结束工作");
        return reutrnVal;
    }
}
public class DynamicProxyTest {
    public static void main(String[] args) {
        //实例化被代理类对象
        SuperMan superMan = new SuperMan();
        //实例化代理类对象
        Human proxy = (Human) ProxyFactory.getProxyInstance(superMan);
        proxy.eat("肉");
        System.out.println(proxy.getName());

    }
}
