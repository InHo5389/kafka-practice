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
public class OrderAnalyticsConsumer {

    @KafkaListener(
            topics = "${kafka.topics.orders}",
            groupId = "order-analytics-group",
            concurrency = "2",
            containerFactory = "orderEventKafkaListenerContainerFactory"
    )
    public void collectAnalytics(
            @Payload OrderEvent orderEvent,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition) {

        log.info("Collecting analytics for order {} from partition {}",
                orderEvent.getOrderId(), partition);

        try {
            updateCustomerStatistics(orderEvent);
        } catch (Exception ex) {
            log.error("Failed to collect analytics for order {}: {}",
                    orderEvent.getOrderId(), ex.getMessage());
        }
    }

    private void updateCustomerStatistics(OrderEvent orderEvent) {
        log.debug("Updated customer statistics for {}", orderEvent.getCustomerId());
    }
}
