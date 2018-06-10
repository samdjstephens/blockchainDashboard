import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicInteger;


public class BitcoinTransactionScraper extends WebSocketClient {
    private static TransactionWriter transactionWriter;
    private static AtomicInteger i = new AtomicInteger(0);
    private static final String transactionURI = "wss://ws.blockchain.info/inv";

    BitcoinTransactionScraper() throws URISyntaxException {
        this(new URI(transactionURI));
    }

    BitcoinTransactionScraper(URI uri) {
        super(uri);
        transactionWriter = TransactionWriter.getInstance();
    }

    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("LedgerScraper open");
        send("{\"op\":\"unconfirmed_sub\"}");
    }

    public void onMessage(String s) {
        System.out.println("Message received");
        transactionWriter.sendTransaction(Integer.toString(i.incrementAndGet()), s);
    }

    public void onClose(int i, String s, boolean b) {
        System.out.println("LedgerScraper closed");
    }

    public void onError(Exception e) {
        System.out.println(e.toString());
    }
}
