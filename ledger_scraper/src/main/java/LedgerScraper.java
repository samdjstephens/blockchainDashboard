import java.net.URISyntaxException;


public class LedgerScraper {
    public static void main(String[] args) throws URISyntaxException {
        BitcoinTransactionScraper scraper = new BitcoinTransactionScraper();
        scraper.connect();
    }
}
