package kafkapractice.config;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.Map;

// 스트림 설정과 카프카 범용적인 설정
@Configuration
@EnableKafkaStreams
public class KafkaStreamConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaStreamConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean("defaultKafkaStreamsConfig")
    public KafkaStreamsConfiguration defaultKafkaStreamsConfig() {
        Map<String, Object> props = kafkaProperties.getStreams().buildProperties(null);

        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        return new KafkaStreamsConfiguration(props);
    }
}
