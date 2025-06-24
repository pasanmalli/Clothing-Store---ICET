package controller.customer;

import db.DBConnection;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerController implements CustomerService {



    @Override
    public boolean addCustomer(Customer customer) {
        boolean isAdd;

        try {
            String SQL = "INSERT INTO customer Values(?,?,?,?,?,?,?,?,?)";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,customer.getId());
            psTm.setObject(2,customer.getTitle());
            psTm.setObject(3,customer.getName());
            psTm.setObject(4,customer.getDob());
            psTm.setObject(5,customer.getAddress());
            psTm.setObject(6,customer.getSalary());
            psTm.setObject(7,customer.getCity());
            psTm.setObject(8,customer.getProvince());
            psTm.setObject(9,customer.getPostal_code());

            isAdd = psTm.executeUpdate() > 0;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (isAdd){
            return true;

        }

        return false;
    }

    @Override
    public ObservableList<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public boolean updateCustomer(Customer customer) {

        String SQL = "UPDATE Customer SET title=?, name=?, dob=?, address=?, salary=?, city=?, province=?, postal_code=? WHERE id=?";
        boolean isUpdate;
        try {


            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,customer.getTitle());
            psTm.setObject(2,customer.getName());
            psTm.setObject(3,customer.getDob());
            psTm.setObject(4,customer.getAddress());
            psTm.setObject(5,customer.getSalary());
            psTm.setObject(6,customer.getCity());
            psTm.setObject(7,customer.getProvince());
            psTm.setObject(8,customer.getPostal_code());
            psTm.setObject(9,customer.getId());

             isUpdate = psTm.executeUpdate() > 0;




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(isUpdate){

            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        boolean isDelete;
        try {
            isDelete = DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM Customer WHERE id = '" + id+ "'") > 0;

            if (isDelete){

                new Alert(Alert.AlertType.INFORMATION,"Deleted Sucessfully").show();


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (isDelete) {

            return true;
        }

        return false;
    }

    @Override
    public Customer serchCustomer(String id) {
        return null;
    }
}
