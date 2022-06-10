package enumTest;
/**
 * 1.枚举类：类的对象只有有限个，称该类为 枚举类
 * 2.当需要定义一组常量，建议使用枚举类
 * 3.如果一个类只有一个对象，则利用单例模式
 *
 * 如何定义枚举类？
 * 1.jdk5.0之前，自定义枚举类
 * 2.jdk5.0时，使用enum关键字
 *      enum关键字修饰的类默认继承与Enum类
 */
public class EnumTest {
    public static void main(String[] args) {
        System.out.println(Season1.WINTER);
        System.out.println("enum关键字修饰的枚举类父类为："+Season1.class.getSuperclass());//java.lang.Enum,不是object

        //values()返回枚举类的所有对象
        Season1[] seasons = Season1.values();
        System.out.println(seasons[1]);//Season{seasonName='夏天', seasonTime='6-8月'}

        //valueOf(String objName)返回指定对象名字的枚举类对象
        Season1 season1 = Season1.valueOf("AUTUMN");
        System.out.println(season1);//Season{seasonName='秋天', seasonTime='9-11月'}
    }
}
interface Info{
    void show();
}

/**
 * 1.Season1类可以重写Info接口的show方法
 * 2.Season1类的每一个对象也可以重写Info接口的show方法
 */
enum Season1 implements Info{
    //1.提供当前枚举类的多个对象：多个对象间用“,”分隔，最后用“;”
    SPRING("春天", "3-5月"){
        @Override
        public void show() {
            System.out.println("万物复苏");
        }
    },
    SUMMER("夏天", "6-8月"){
        @Override
        public void show() {
            System.out.println("炎炎夏日");
        }
    },
    AUTUMN("秋天", "9-11月"){
        @Override
        public void show() {
            System.out.println("秋高气爽");
        }
    },
    WINTER("冬天", "12-2月"){
        @Override
        public void show() {
            System.out.println("冰天雪地");
        }
    };
    //2.声明season对象属性:private final修饰
    private final String seasonName;
    private final String seasonTime;
    //3.私有化构造器,并给对象属性赋值
    Season1(String seasonName, String seasonTime){
        this.seasonName = seasonName;
        this.seasonTime = seasonTime;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonTime() {
        return seasonTime;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonTime='" + seasonTime + '\'' +
                '}';
    }
}
