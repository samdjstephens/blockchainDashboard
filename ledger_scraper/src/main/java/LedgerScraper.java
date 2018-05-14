import java.net.URI;
import java.net.URISyntaxException;


public class LedgerScraper {
    public static void main(String[] args) throws URISyntaxException {
        BitcoinTransactionScraper scraper = new BitcoinTransactionScraper(new URI("wss://ws.blockchain.info/inv"));
        scraper.connect();
    }
}
