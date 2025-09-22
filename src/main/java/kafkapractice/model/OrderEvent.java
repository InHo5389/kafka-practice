package kafkapractice.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderEvent {

    private String orderId;
    private String customerId;
    private int quantity;
    private BigDecimal price;
    private String eventType = "ORDER_CREATED";
    private String status = "PENDING";

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();
}
