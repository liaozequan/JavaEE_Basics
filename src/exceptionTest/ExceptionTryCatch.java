package exceptionTest;

import org.junit.Test;

/**
 * 异常的处理：抓抛模型
 *  过程一，“抛”：程序在执行过程中，一旦出现异常，就会在异常代码出生成一个异常对象，并将次对象抛出
 *              一旦抛出对象后，下面的代码就不再运行
 *         异常对象的产生：①系统自动生成异常对象
 *                      ②手动生成一个异常对象，并抛出： throw new exceptionClass
 *  过程二，“抓”，可以理解为异常处理的方式：①try-catch-finally ②throws
 *
 * ①try-catch-finally
 *  try{
 *      //可能出现异常的代码
 *  }catch(异常类型1 变量名1){
 *      //异常处理方式1
 *  }catch(异常类型2 变量名2){
 *      //异常处理方式2
 *  }finally{
 *      //一定会执行的代码
 *  }
 *  * 1.如果先catch高级异常，在catch子类异常，会报错：子类异常已被捕获
 *  * 2.如果先catch子类异常，在catch高级异常，子类异常被捕获后，不会再捕获高级父类异常
 *  * 3.异常处理方式：e.getMessage()   e.printStackTrace()
 *  * 4.在try结构中声明的变量，出了try结构后，就不能被调用
 *
 *  finally作用：
 *  1.如果在try结构中有return，或者在catch结构中还有异常或return，
 *  finally就体现出他的作用，即使return或catch中出现异常，仍然会执行finally中代码
 *  2.如果catch中有return，finally中也有return，则最终会在finally中return
 *  3.什么时候用finally？像数据库连接、输入输出流、socket等资源，JVM不能自动回收，需要我们手动回收，就需要finally
 */
public class ExceptionTryCatch {
    @Test
    public void test1() {
        String str = "123";
        str = "abc";

        try {
            if("abc".equals(str)){
                return;
            }
            int num = Integer.parseInt(str);
            //上面出现异常，下面代码终止运行
            System.out.println("********");
        } catch (NullPointerException e) {
            //不需要空指针异常就不会执行
            System.out.println("空指针异常！");
            /**
             * 如果先catch高级异常，在catch子类异常，会报错：子类异常已被捕获
             * 如果先catch子类异常，在catch高级异常，子类异常被捕获后，不会再捕获高级父类异常
             */
//        }catch (Exception e){
//            System.out.println("出现异常！");
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage()); //仅报错异常信息
            e.printStackTrace();                //报错异常信息、位置等，就是不写try-catch时出现的
            System.out.println("数值转换异常！");
        } catch (Exception e) {
            System.out.println("出现异常！");
        }finally {
            System.out.println("结束！！！！！！！！！！");
        }
    }
}
