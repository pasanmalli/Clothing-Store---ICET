package controller.item;

import javafx.collections.ObservableList;
import model.Customer;
import model.Item;
import model.OrderDetail;

import java.util.List;

public interface ItemService {

    boolean addItem(Item item);

    ObservableList<Item> getAllItems();

    boolean updateItem(Item item);

    boolean deleteItem(String itemCode);

    Item serchItem(String itemCode );

    List<String> getAllItemCodes();


    boolean updateStock(List<OrderDetail> orderDetails);
}
