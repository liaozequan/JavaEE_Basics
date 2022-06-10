package fileTest;

import org.junit.Test;

import java.io.File;

/**
 * 1.File类的一个对象，表示一个文件或一个文件目录
 * 2.File类声明在java.IO包下
 * 3.File类的对象仅仅操作文件（目录）本身，涉及到修改文件中的内容需要IO流
 * 4.File的对象会作为参数传递到流的构造器中，用来指明读写(IO)的对象
 */
public class FileAndMethod {
    @Test
    public void test1(){
        //创建File类的对象实例
        /**构造器1 File(String pathname)
         */
        //使用相对路径（当前module下）
        File file1 = new File("hello.txt");
        //使用绝对路径,路径分隔符\\可以替换为File.separator,可以支持跨平台。因为win中\\,在unix中/
        File file2 = new File("E:"+File.separator+"idea-workspace\\JavaEE_Basics\\File&Generic\\hello.txt");

        /**构造器2 File(String parentPath, String childPath)
         */
        File file3 = new File("E:","\\idea-workspace\\JavaEE_Basics\\File&Generic");

        /**构造器3 File(File parentPath, String childPath)
         */
        File file4 = new File(file3,"hello.txt");
        System.out.println(file4);
    }

    /**File类方法
     * getAbsolutePath():获取文件绝对路径
     * getPath():相对路径
     * getName()获取文件名
     * getParent()获取文件所在文件夹名，如没有返回null
     * length()文件长度（字节数）
     * lastModified()最近修改时间
     * String[] list()获取指定路径下所有文件或文件目录名称
     * File[] listFiles()获取指定路径下所有文件或文件目录对象
     *
     * isDirectory()是否是目录
     * isFile()是否是文件
     * exists()是否存在
     *
     * 创建/删除文件（夹）
     * boolean createNewFile()创建文件，若文件存在，则不创建
     * boolean mkdir()创建文件目录。若该目录存在，则不创建。若该目录的上层目录不存在，则也不创建
     * boolean mkdirs()创建文件目录，若该目录的上层目录不存在，则一并创建
     * boolean delete()删除文件(夹)，删除不进入回收站，若删除的文件夹中有东西，则无法删除
     */
}
