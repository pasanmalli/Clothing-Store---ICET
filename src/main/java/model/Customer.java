package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String id;
    private String title;
    private String name;
    private LocalDate dob;
    private String address;
    private Double salary;
    private String city;
    private String province;
    private String postal_code;


}
