package annotationTest;

/**
 * 元注解：对注解进行解释说明的注解（给注解使用的注解）
 * 1.Retention：指定所修饰的注解的生命周期（SOURCE,CLASS(默认),RUNTIME）
 *      只有声明为RUNTIME的注解才能被反射获取
 * 2.Target:用于指定被修饰的annotation能用于修饰哪些程序元素（如类、方法、成员变量、局部变量等）
 * 3.Inherited:被它修饰的annotation，当有父类使用此annotation时，子类也会有此annotation
 */
public class MetaAnnotationTest {
}

