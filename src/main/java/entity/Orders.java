package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Orders {
    @Id
    private String orderId;
    private String customerCode;
    private String itemCode;
    private String fault;
    private int price;

}
