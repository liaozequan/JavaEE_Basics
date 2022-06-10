package enumTest;

/**
 * 1.枚举类：类的对象只有有限个，称该类为 枚举类
 * 2.当需要定义一组常量，建议使用枚举类
 * 3.如果一个类只有一个对象，则利用单例模式
 *
 * 如何定义枚举类？
 * 1.jdk5.0之前，自定义枚举类
 * 2.jdk5.0时，使用enum关键字
 */
public class CustomEnumTest {
    public static void main(String[] args) {
        //方法一：自定义枚举类
        System.out.println(Season.SPRING);
    }

}
//方法一：自定义枚举类
class Season{
    //1.声明season对象属性:private final修饰
    private final String seasonName;
    private final String seasonTime;
    //2.私有化构造器,并给对象属性赋值
    private Season(String seasonName, String seasonTime){
        this.seasonName = seasonName;
        this.seasonTime = seasonTime;
    }
    //3.实例化当前枚举类的多个对象：public static final修饰
    public static final Season SPRING = new Season("春天", "3-5月");
    public static final Season SUMMER = new Season("夏天", "6-8月");
    public static final Season AUTUMN = new Season("秋天", "9-11月");
    public static final Season WINTER = new Season("冬天", "12-2月");

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
