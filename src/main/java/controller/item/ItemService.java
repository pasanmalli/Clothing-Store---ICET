package controller.item;

import javafx.collections.ObservableList;
import model.Customer;
import model.Item;

public interface ItemService {

    boolean addItem(Item item);

    ObservableList<Item> getAllItems();

    boolean updateItem(Item item);

    boolean deleteItem(String itemCode);

    Item serchItem(String itemCode );



}
