package kafkapractice.messaging;

import kafkapractice.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventConsumer {

    @KafkaListener(
            topics = "${kafka.topics.orders}",
            groupId = "order-processing-group",
            concurrency = "3",
            containerFactory = "orderEventKafkaListenerContainerFactory"
    )
    public void processOrder(
            @Payload OrderEvent orderEvent,
            @Header(KafkaHeaders.RECEIVED_PARTITION) String partition,
            @Header(KafkaHeaders.OFFSET) Long offset) {
        log.info("Received order event: {}", orderEvent);

        try {
            processLogic();
            log.info("Received order event: {}", orderEvent);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private void processLogic() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted", e);
        }
    }
}
