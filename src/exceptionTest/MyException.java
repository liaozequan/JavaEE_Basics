package exceptionTest;

/**
 * 自定义异常类，
 * 1.需要继承现有的异常
 * 2.提供serialVersionUID
 * 3.提供构造器
 */
public class MyException extends RuntimeException{
    static final long serialVersionUID = -7034897190756466939L;

    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}
