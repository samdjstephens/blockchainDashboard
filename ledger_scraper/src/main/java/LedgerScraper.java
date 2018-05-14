import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class LedgerScraper extends WebSocketClient {
    public LedgerScraper(URI uri) {
        super(uri);
    }

    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("LedgerScraper open");
        send("{\"op\":\"unconfirmed_sub\"}");
    }

    public void onMessage(String s) {
        System.out.println(s);
    }

    public void onClose(int i, String s, boolean b) {
        System.out.println("LedgerScraper closed");
    }

    public void onError(Exception e) {
        System.out.println(e.toString());
    }
}
