package lambdaTest;

/**
 * 函数式接口：该接口中只有一个抽象方法，且有@FunctionalInterface注解（也可没有注解）
 * 加了注解后，然后给接口中声明两个方法编译器会报错（用于检验是否是函数式接口）
 * lambda本质：作为接口（要求函数式接口）的实例
 */
@FunctionalInterface
public interface MyFunctionalInterface {
    void method();
}
