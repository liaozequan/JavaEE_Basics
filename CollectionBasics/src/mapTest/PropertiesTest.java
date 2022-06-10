package mapTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        //获取文件
        FileInputStream fis = new FileInputStream("properties.properties");
        //加载文件
        properties.load(fis);
        String name = properties.getProperty("name");
        String address = properties.getProperty("address");
        fis.close();


    }
}
