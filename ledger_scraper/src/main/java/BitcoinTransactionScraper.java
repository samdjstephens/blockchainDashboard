import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicLong;


public class BitcoinTransactionScraper extends WebSocketClient {
    private static TransactionWriter transactionWriter;
    private static AtomicLong i = new AtomicLong(0);
    private static final String TRANSACTION_URI = "wss://ws.blockchain.info/inv";

    BitcoinTransactionScraper() throws URISyntaxException {
        this(new URI(TRANSACTION_URI));
    }

    BitcoinTransactionScraper(URI uri) {
        super(uri);
        // TODO: Should be injected
        transactionWriter = TransactionWriter.getInstance();
    }

    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("LedgerScraper open");
        send("{\"op\":\"unconfirmed_sub\"}");
    }

    public void onMessage(String s) {
        System.out.println("Message received");
        transactionWriter.sendTransaction(i.incrementAndGet(), s);
    }

    public void onClose(int i, String s, boolean b) {
        System.out.println("LedgerScraper closed");
    }

    public void onError(Exception e) {
        System.out.println(e.toString());
    }
}
