package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Supplier {




    private String supplierId;
    private String supplierName;
    private String supplierEmail;
    private String product;
    private Integer productQty;







}
