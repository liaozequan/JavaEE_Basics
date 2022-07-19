package sync;

import java.util.concurrent.TimeUnit;

/**
 * 研究不同同步方法，是否用同一把锁？
 * 例子8种情况
 * 普通同步方法：锁是当前对象
 * 静态同步方法，锁是当前Class运行时类
 * 同步方法块，锁是括号内的对象
 */
class Phone{
    //情况1，2，3，4
//    public synchronized void sendSMS() throws InterruptedException {
//        //情况2：停4s在短信方法中
//        TimeUnit.SECONDS.sleep(4);
//        System.out.println("----sendSMS");
//    }
//    public synchronized void sendEmail(){
//        System.out.println("----sendEmail");
//    }

    //情况5，6 静态同步方法
    public static synchronized void sendSMS() throws InterruptedException {
    //情况2：停4s在短信方法中
    TimeUnit.SECONDS.sleep(4);
    System.out.println("----sendSMS");
    }
    public synchronized void sendEmail(){
        System.out.println("----sendEmail");
    }
    //情况3：声明一个普通打印方法
    public void getHello(){
        System.out.println("----getHello");
    }
}

/**8种锁
 * 1.标准访问，先打印短信还是打印邮件？：先打印短信，再打印邮件
 * 2.停4s在短信方法中，先打印短信还是打印邮件？：先在短信方法中停4s再打印短信，再打印邮件
 * 情况1，2都是普通同步方法，使用同一个对象来调用两个同步方法，
 * 当一个同步方法上锁后，是给这个对象上锁，因此另一个同步方法也被锁了
 *
 * 3.一个普通getHello（无synchronized修饰）,先停4s在短信方法中，先打印短信还是打印hello？
 * 直接打印hello，等4s打印短信
 * 情况3：getHello不是同步方法，即使sendSMS()给对象上锁了，getHello不会被锁，仍然可以执行
 *
 * 4.实例化两边手机，先打印短信还是打印邮件？：第二部手机直接打印邮件，等4s第一部手机打印短信
 * 情况4：sendSMS()只是锁了第一部手机，而第二部手机不受影响，因此第二部手机可以直接打印邮件
 *
 * 5.两个静态同步方法，1部手机先打印短信还是打印邮件？：先在短信方法中停4s再打印短信，再打印邮件
 * 6.两个静态同步方法，2部手机先打印短信还是打印邮件？：先在短信方法中停4s再打印短信，再打印邮件
 * 情况5，6都是静态同步方法，当一个同步方法上锁后，锁的是Phone.Class(运行时类)
 * 因此不管是同一个对象还是不同对象，只要Class被锁，同步方法就被锁了
 *
 * 7.一个静态同步方法，一个普通同步方法，1部手机先打印短信还是打印邮件？：
 * sendSMS是静态同步(方法内等4s)，sendEmail是普通同步：直接打印邮件，等4s打印短信
 * 8.一个静态同步方法，一个普通同步方法，2部手机先打印短信还是打印邮件？：
 * sendSMS是静态同步(方法内等4s)，sendEmail是普通同步：直接打印邮件，等4s打印短信
 * 情况7，8：静态同步方法锁的是Class运行时类，而普通同步方法锁的是当前对象，因此有两把锁，各不影响。
 */
public class Lock_8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone(); //情况4，再实例化第二部手机
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        Thread.sleep(100);

        new Thread(()->{
            //情况1，5
//            phone.sendEmail();
            //情况3，调用getHello()
//            phone.getHello();
            //情况4，6，用第二部手机调用sendEmail()
            phone2.sendEmail();
        }, "B").start();
    }
}
