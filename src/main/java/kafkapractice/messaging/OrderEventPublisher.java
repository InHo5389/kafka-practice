package kafkapractice.messaging;

import kafkapractice.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Value("${kafka.topics.orders}")
    private final String ordersTopic;

    public void publishOrderEvent(OrderEvent orderEvent) {
        try {
            kafkaTemplate.send(ordersTopic, orderEvent.getOrderId(), orderEvent)
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            log.error("Error when publishing order event", ex);
                        } else {
                            log.info("Successfully published order event");
                        }
                    });
        } catch (Exception ex) {
            log.error("Error Publishing order event", ex);
        }
    }
}
