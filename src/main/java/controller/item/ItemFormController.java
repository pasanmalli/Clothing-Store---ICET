package controller.item;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colDecscription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQTY;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<Item> tblItems;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtQTY;

    @FXML
    private JFXTextField txtUnitPrice;

    ItemService service = new ItemController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDecscription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));

        tblItems.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newVal) ->{

            if (newVal!=null) {
                addValueToText((Item) newVal);
            }


        } );


        loadtable();

    }

    private void addValueToText(Item newVal) {


        txtItemCode.setText(newVal.getItemCode());
        txtDescription.setText(newVal.getDescription());
        txtPackSize.setText(newVal.getPackSize());
        txtUnitPrice.setText(newVal.getUnitPrice().toString());
        txtQTY.setText(newVal.getQty().toString());

    }


    @FXML
    void btnAddOnAction(ActionEvent event) {





        if(service.addItem(

                new Item(
                        txtItemCode.getText(),
                        txtDescription.getText(),
                        txtPackSize.getText(),
                        Double.parseDouble(txtUnitPrice.getText()),
                        Integer.parseInt(txtQTY.getText())
                )

        )){

            new Alert(Alert.AlertType.INFORMATION,"Item Added!!").show();
            loadtable();

        }
        else {

            new Alert(Alert.AlertType.ERROR,"Item Not Added!!").show();

        }




    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {



        if ( service.deleteItem(txtItemCode.getText())){

            new Alert(Alert.AlertType.INFORMATION,"Item Deleted!!").show();
            loadtable();

        }

        else {

            new Alert(Alert.AlertType.ERROR,"Item Not Deleted!!").show();

        }


    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        Item item = service.serchItem(txtItemCode.getText());

        txtDescription.setText(item.getDescription());
        txtPackSize.setText(item.getPackSize());
        txtUnitPrice.setText(item.getUnitPrice().toString());
        txtQTY.setText(item.getQty().toString());


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {





                if(service.updateItem(
                        new Item(
                                txtItemCode.getText(),
                                txtDescription.getText(),
                                txtPackSize.getText(),
                                Double.parseDouble(txtUnitPrice.getText()),
                                Integer.parseInt(txtQTY.getText())
                        )


                )){

                    new Alert(Alert.AlertType.INFORMATION,"Item Updated!!").show();

                    loadtable();

                }

                else {

                    new Alert(Alert.AlertType.INFORMATION,"Item Not Updated!!").show();


                }

    }


   private void loadtable(){

      tblItems.setItems(service.getAllItems());


    }


}
