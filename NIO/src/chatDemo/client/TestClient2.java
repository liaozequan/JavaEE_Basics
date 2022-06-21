package chatDemo.client;

import java.io.IOException;

public class TestClient2 {
    public static void main(String[] args) {
        try {
            new ChatClient().startClient("zsss");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
