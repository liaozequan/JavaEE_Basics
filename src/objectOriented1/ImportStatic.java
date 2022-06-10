package objectOriented1;
import static java.lang.System.out;
import static java.lang.Math.*;

/**
 * System类下有静态属性 public final static PrintStream out = null;
 * 可以通过import导入某个package中的某个class中的某个static属性
 */
public class ImportStatic {
    public static void main(String[] args) {
        //System.out.println();
        out.println();
        //Math.max(1,2);
        max(1,2);
    }
}
