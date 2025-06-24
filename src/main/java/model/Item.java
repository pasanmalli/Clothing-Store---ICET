package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {

    String itemCode;
    String description;
    String packSize;
    Double unitPrice;
    Integer qty;


}
