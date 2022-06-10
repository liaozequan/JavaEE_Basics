package exceptionTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *异常处理方式二，抓：throws+异常类型
 * 抛出异常的方法不会处理异常，会让调用它的方法处理
 */
public class ExceptionThrows {
    public void receiveException(){
        /**
         * test1()抛出了异常，可以在这里处理，也可以再往上抛
         */
        try {
            test1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 抛出的异常会在调用方法中处理
     */
    public void test1() throws FileNotFoundException, IOException{
        File file = new File("Hello.txt");
        FileInputStream fis = null;
        fis = new FileInputStream(file);
        fis.close();

    }

    public void test2(){
        File file = new File("Hello.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
