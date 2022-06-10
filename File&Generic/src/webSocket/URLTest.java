package webSocket;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLTest {
    public static void main(String[] args){
        HttpURLConnection urlConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            URL url = new URL("http:");
            urlConnection = (HttpURLConnection)url.openConnection();
            //访问url
            urlConnection.connect();
            //从服务器中得到输入流
            is = urlConnection.getInputStream();
            //将数据写入磁盘
            fos = new FileOutputStream("china.jpeg");
            byte[] buffer = new byte[1024*1024];
            int len;
            while ((len=is.read())!=-1){
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                //断开连接
                if(urlConnection != null)
                    urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
