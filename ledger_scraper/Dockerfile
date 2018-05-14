FROM maven:3

COPY . /root/ledger_scraper
WORKDIR /root/ledger_scraper

RUN mvn clean package

CMD java -cp target/ledger_scraper-1.0-SNAPSHOT.jar:target/lib/Java-WebSocket-1.3.8.jar LedgerScraper