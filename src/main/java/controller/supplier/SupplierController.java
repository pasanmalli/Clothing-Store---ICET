package controller.supplier;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Item;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierController implements SupplierService {
    @Override
    public boolean addSupplier(Supplier supplier) {


        boolean isAdd;
        try {

            String SQL = "INSERT INTO supplier VALUES(?,?,?,?,?)";
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement psTm = connection.prepareStatement(SQL);

            psTm.setObject(1,supplier.getSupplierId());
            psTm.setObject(2,supplier.getSupplierName());
            psTm.setObject(3,supplier.getSupplierEmail());
            psTm.setObject(4,supplier.getProduct());
            psTm.setObject(5,supplier.getProductQty());

            isAdd = psTm.executeUpdate() > 0;

            //System.out.println(isAdd);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(isAdd){

           return true;


        }

        return false;



    }

    @Override
    public ObservableList<Supplier> getSupplier() {

        ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();

        String SQL = "SELECT * FROM supplier";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);

            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()){



                Supplier supplier = new Supplier(

                        resultSet.getString("supplierId")
                        ,resultSet.getString("supplierName")
                        , resultSet.getString("supplierEmail")
                        , resultSet.getString("product")
                        , resultSet.getInt("productQty")


                );


                supplierObservableList.add(supplier);










            }

            return supplierObservableList;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        boolean isUpdated;
        String SQL = "UPDATE supplier SET supplierName=?, supplierEmail=?, product=?, productQty=? WHERE supplierId=?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,supplier.getSupplierName());
            psTm.setObject(2,supplier.getSupplierEmail());
            psTm.setObject(3,supplier.getProduct());
            psTm.setObject(4,supplier.getProductQty());
            psTm.setObject(5,supplier.getSupplierId());

            isUpdated = psTm.executeUpdate() > 0;

            //System.out.println("true");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        if (isUpdated) {

            return true;

        }


        return false;

    }

    @Override
    public boolean deleteSupplier(String id) {

        boolean isDeleted;

        try {
            isDeleted = DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM supplier WHERE supplierId = '" + id + "'") > 0;




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (isDeleted){

            return true;



        }

        else{

            return false;


        }

    }

    @Override
    public Supplier searchSupplier(String id) {


        String SQL="SELECT * FROM supplier WHERE supplierId='"+id+"'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                return new Supplier(
                        resultSet.getString(1)
                        ,resultSet.getString(2)
                        ,resultSet.getString(3)
                        ,resultSet.getString(4)
                        ,resultSet.getInt(5)


                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;}


}
