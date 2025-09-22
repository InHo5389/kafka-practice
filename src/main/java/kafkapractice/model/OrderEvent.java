package kafkapractice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {

    private String orderId;
    private String customerId;
    private int quantity;
    private BigDecimal price;
    private String eventType = "ORDER_CREATED";
    private String status = "PENDING";

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    public OrderEvent(String orderId, String customerId, int quantity, BigDecimal price) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.price = price;
    }
}
