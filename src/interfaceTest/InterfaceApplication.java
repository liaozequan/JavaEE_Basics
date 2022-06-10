package interfaceTest;

/**接口的多态性
 * 接口就是定义了规范\功能，比如某个类需要有什么功能，就去实现什么接口
 *  本段程序还练习了接口的匿名实现类
 */
public class InterfaceApplication {
    public static void main(String[] args) {
        Computer computer = new Computer();
        //非匿名对象非匿名类
        FlashMemory flashMemory = new FlashMemory();
        computer.transferData(flashMemory);

        //匿名对象非匿名类
        computer.transferData(new Printer());

        //非匿名对象匿名类
        USB phone = new USB() {
            @Override
            public void start() {

            }

            @Override
            public void stop() {

            }
        };
        computer.transferData(phone);

        //匿名对象匿名类
        computer.transferData(new USB() {
            @Override
            public void start() {

            }

            @Override
            public void stop() {

            }
        });
    }
}
class Computer{
    public void transferData(USB usb){
        usb.start();
        System.out.println("传输数据");
        usb.stop();
    }
}


interface USB{
    void start();

    void stop();
}

class FlashMemory implements USB{
    @Override
    public void start() {
        System.out.println("U盘开始工作");
    }

    @Override
    public void stop() {
        System.out.println("U盘结束工作");
    }
}

class Printer implements USB{
    @Override
    public void start() {
        System.out.println("打印机开始工作");
    }

    @Override
    public void stop() {
        System.out.println("打印机开始工作");
    }
}
