package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {


    private String empId;
    private String empName;
    private String empEmail;
    private LocalDate joinDate;
}
