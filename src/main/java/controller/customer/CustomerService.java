package controller.customer;

import javafx.collections.ObservableList;
import model.Customer;

import java.util.List;

public interface CustomerService {

    boolean addCustomer(Customer customer);

    ObservableList<Customer> getAllCustomers();

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(String id);

    Customer serchCustomer(String id);


    List<String> getAllCustomerIds();



}
