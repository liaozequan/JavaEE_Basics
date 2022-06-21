package chatDemo.client;

import java.io.IOException;

public class TestClient1 {
    public static void main(String[] args) {
        try {
            new ChatClient().startClient("lzq");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
