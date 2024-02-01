package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDto {
    private String orderId;
    private String customerCode;
    private String itemCode;
    private String fault;
    private int price;

}
