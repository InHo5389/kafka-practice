package kafkapractice.messaging;

import kafkapractice.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderNotificationConsumer {

    @KafkaListener(
            topics = "${kafka.topics.orders}",
            groupId = "order-notification-group",
            concurrency = "1",
            containerFactory = "orderEventKafkaListenerContainerFactory"
    )
    public void sendNotifications(
            @Payload OrderEvent orderEvent,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition) {

        log.info("Sending notifications for order {} from partition {}",
                orderEvent.getOrderId(), partition);

        try {
            if (isHighValueOrder(orderEvent)) {
                sendHighValueOrderSms(orderEvent);
            }
        } catch (Exception ex) {
            log.error("Failed to send notifications for order {}: {}",
                    orderEvent.getOrderId(), ex.getMessage());
        }
    }

    private void sendHighValueOrderSms(OrderEvent orderEvent) {
        log.info("SMS sent for high-value order {}", orderEvent.getOrderId());
    }

    private boolean isHighValueOrder(OrderEvent orderEvent) {
        return orderEvent.getPrice().compareTo(new BigDecimal("1000")) >= 0;
    }
}
