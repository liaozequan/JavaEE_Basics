package exceptionTest;

/**
 * 手动抛出异常对象：throw
 */
public class ExceptionThrow {
    public static void main(String[] args) {
        Student s = new Student();
        try {
            s.regist(-1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class Student{
    private int id;
    public void regist(int id) throws Exception{
        if(id>0){
            this.id = id;
        }else{
            //手动抛出异常对象
            //throw new RuntimeException("输入数据非法");
            //或者抛出Exception,需要在调用时解决
            throw new Exception("输入数据非法");
        }
    }
}
