package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService {

 private static CustomerController instance;


    private CustomerController() {}

    public static CustomerController getInstance(){

   return instance==null?instance=new CustomerController():instance;

    }

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

        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();


        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer");

            while (resultSet.next()){

                customerObservableList.add(

                        new Customer(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDate(4).toLocalDate(),
                                resultSet.getString(5),
                                resultSet.getDouble(6),
                                resultSet.getString(7),
                                resultSet.getString(8),
                                resultSet.getString(9)


                        )



                );




            }

            return customerObservableList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



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
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer WHERE id=?", id);

            while (resultSet.next()){

            return new Customer(

                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4).toLocalDate(),
                        resultSet.getString(5),
                        resultSet.getDouble(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)



                );


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }


    @Override
    public List<String> getAllCustomerIds(){


        ObservableList<Customer> customerObservableList = getAllCustomers();

        ArrayList<String> cusIdsList = new ArrayList<>();

        customerObservableList.forEach(obj ->{

         cusIdsList.add(obj.getId());

                }

        );

        return cusIdsList;


    }

}
