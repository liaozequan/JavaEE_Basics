package exceptionTest;

import java.io.IOException;

/**
 * 1.子类重写父类方法，抛出的异常要不大于父类方法抛出的异常
 * 2.如果父类方法没有抛出异常，子类重写方法也不能抛出，可以try-catch异常
 */
public class ExceptionOverwrite {

}
class SuperClass{
    public void test()throws IOException {

    }
}

class SubClass extends SuperClass{
    public void test()throws IOException{

    }
}
