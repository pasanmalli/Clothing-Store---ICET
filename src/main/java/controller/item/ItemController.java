package controller.item;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.OrderDetail;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements ItemService{


    private static ItemController instance;

    private ItemController(){}

    public static ItemController getInstance(){

        return instance==null?instance=new ItemController():instance;
    }




    @Override
    public boolean addItem(Item item) {


        String SQL = "INSERT INTO item Values(?,?,?,?,?)";
        try {


            Object execute = CrudUtil.execute(SQL,
                    item.getItemCode(),
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getQty());

            System.out.println(execute);

            return true;




            //Connection connection = DBConnection.getInstance().getConnection();
            //PreparedStatement psTm = connection.prepareStatement(SQL);
            //psTm.setObject(1,item.getItemCode());
            //psTm.setObject(2,item.getDescription());
            //psTm.setObject(3,item.getPackSize());
            //psTm.setObject(4,item.getUnitPrice());
            //psTm.setObject(5,item.getQty());

            //return psTm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override


    public ObservableList<Item> getAllItems() {

        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();

        String SQL ="SELECT * FROM item";
        try {
            //Connection connection = DBConnection.getInstance().getConnection();
            //PreparedStatement psTm = connection.prepareStatement(SQL);
            //ResultSet resultSet = psTm.executeQuery();

            ResultSet resultSet = CrudUtil.execute(SQL);


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

            Object execute = CrudUtil.execute(SQL, item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getQty(),
                    item.getItemCode());

            System.out.println(execute);

            return true;


            //Connection connection = DBConnection.getInstance().getConnection();
            //PreparedStatement psTm = connection.prepareStatement(SQL);
            //psTm.setObject(1,item.getDescription());
            //psTm.setObject(2,item.getPackSize());
            //psTm.setObject(3,item.getUnitPrice());
            //psTm.setObject(4,item.getQty());
            //psTm.setObject(5,item.getItemCode());
            //return psTm.executeUpdate()>0;

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
    
    
    @Override
    public List<String> getAllItemCodes(){

        ArrayList<String> itemCodeList = new ArrayList<>();
        ObservableList<Item> allItems = getAllItems();

        allItems.forEach(obj->{

            itemCodeList.add(obj.getItemCode());

        });

        return itemCodeList;


    }

    @Override
    public boolean updateStock(List<OrderDetail> orderDetails) {

        for (OrderDetail orderDetail : orderDetails ){

            boolean isUpdateStock = updateStock(orderDetail);

            if(!isUpdateStock){

                return false;
            }

        }
        return true;
    }

    public boolean updateStock(OrderDetail orderDetail){

        String SQL = "Update Item SET qtyOnHand=qtyOnHand-? WHERE code=?";
        try {
           return CrudUtil.execute(SQL,orderDetail.getQty(),orderDetail.getItemCode());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
