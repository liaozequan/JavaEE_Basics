package annotationTest;

/**自定义注解
 * 以 @SuppressWarnings 为模版来定义注解
 * annotation中的成员变量以无参方法来声明，其方法名和返回值定义了该变量的名字和数据类型，我们称为配置参数
 * 如果只有一个变量，建议使用value
 * 可以使用default为变量定义一个默认值
 * 自定义注解必须具有信息处理流程（利用反射）才有意义
 */
public @interface MyAnnotation {
    String value() default "hello";
}
