package kafkapractice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class CreateOrderRequest {

    private String customerId;
    private Integer quantity;
    private BigDecimal price;
}
