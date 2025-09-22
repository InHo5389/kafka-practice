package kafkapractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@EnableKafka
@EnableKafkaStreams
@SpringBootApplication
public class KafkaPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaPracticeApplication.class, args);
	}

}
