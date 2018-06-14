import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

class TransactionWriter {
    static final String TOPIC = "bitcoin-transactions";
    private final Producer<Long, String> kafkaProducer;
    private static TransactionWriter INSTANCE;

    static TransactionWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TransactionWriter();
        }
        return INSTANCE;
    }

    private TransactionWriter() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.LongSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.CLIENT_ID_CONFIG,
                "BitcoinTransactionWriter");

        this.kafkaProducer = new KafkaProducer<>(props);
    }

    void sendTransaction(Long key, String value) {
        try {
            ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, key, value);
            // TODO: Remove after development is complete
            RecordMetadata metadata = this.kafkaProducer.send(record).get(3, TimeUnit.SECONDS);
            System.out.println(metadata.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
