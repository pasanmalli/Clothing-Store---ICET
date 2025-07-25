package controller.employee;

import javafx.collections.ObservableList;
import model.Customer;
import model.Employee;

public interface EmployeeService {

    boolean addEmployee(Employee employee);

    ObservableList<Employee> getAllEmployees();

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(String id);

    Employee searchEmployee(String empId);




}
