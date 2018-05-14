import java.net.URI;
import java.net.URISyntaxException;

public class BitcointTransactionScraper {
    public static void main(String[] args) throws URISyntaxException {
        LedgerScraper ledgerScraper = new LedgerScraper(new URI("wss://ws.blockchain.info/inv"));
        ledgerScraper.connect();
    }
}
