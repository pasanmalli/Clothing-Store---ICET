package controller.item;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemController implements ItemService{
    @Override
    public boolean addItem(Item item) {

        try {
            String SQL = "INSERT INTO item Values(?,?,?,?,?)";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,item.getItemCode());
            psTm.setObject(2,item.getDescription());
            psTm.setObject(3,item.getPackSize());
            psTm.setObject(4,item.getUnitPrice());
            psTm.setObject(5,item.getQty());

            return psTm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override


    public ObservableList<Item> getAllItems() {

        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();

        String SQL ="SELECT * FROM item";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()){

                itemObservableList.add(

                new Item(
                        resultSet.getString(1)
                        ,resultSet.getString(2)
                        ,resultSet.getString(3)
                        ,resultSet.getDouble(4)
                        ,resultSet.getInt(5)
                ));



            }


        return itemObservableList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean updateItem(Item item) {
        String SQL = "UPDATE item SET description=?, packSize=?, unitPrice=?, QtyOnHand=? WHERE code=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,item.getDescription());
            psTm.setObject(2,item.getPackSize());
            psTm.setObject(3,item.getUnitPrice());
            psTm.setObject(4,item.getQty());
            psTm.setObject(5,item.getItemCode());
            return psTm.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteItem(String itemCode) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate("DELETE FROM item WHERE code = '"+itemCode+"'")>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Item serchItem(String itemCode) {

        String SQL="SELECT * FROM item WHERE code='"+itemCode+"'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
              return new Item(
                        resultSet.getString(1)
                        ,resultSet.getString(2)
                        ,resultSet.getString(3)
                        ,resultSet.getDouble(4)
                        ,resultSet.getInt(5)


              );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    return null;}
}
