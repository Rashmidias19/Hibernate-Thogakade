package lk.ijse.thogakade.dto;

/*
    @author DanujaV
    @created 3/30/23 - 10:13 AM   
*/

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDTO {
    private String code;
    private Integer qty;
    private Double unitPrice;
}
