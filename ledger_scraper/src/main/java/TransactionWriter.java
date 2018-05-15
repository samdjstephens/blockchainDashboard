import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

class TransactionWriter {
    static final String TOPIC = "bitcoin-transactions";
    private final Producer<String, String> kafkaProducer;
    private static TransactionWriter INSTANCE;

    static TransactionWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TransactionWriter();
        }
        return INSTANCE;
    }

    private TransactionWriter() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "kafka:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.kafkaProducer = new KafkaProducer<>(props);
    }

    void sendTransaction(String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, key, value);
        this.kafkaProducer.send(record);
    }
}
