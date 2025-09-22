package kafkapractice.controller;

import kafkapractice.messaging.OrderEventPublisher;
import kafkapractice.model.CreateOrderRequest;
import kafkapractice.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/lecture")
@RequiredArgsConstructor
public class TestController {

    private final OrderEventPublisher orderEventPublisher;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest request) {
        OrderEvent orderEvent = new OrderEvent(
                UUID.randomUUID().toString(),
                request.getCustomerId(),
                request.getQuantity(),
                request.getPrice()
        );
        orderEventPublisher.publishOrderEvent(orderEvent);

        return ResponseEntity.ok("Order created");
    }
}
