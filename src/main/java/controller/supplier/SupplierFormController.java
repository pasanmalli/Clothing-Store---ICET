package controller.supplier;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Supplier;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbProduct;

    @FXML
    private TableColumn<?, ?> colProduct;

    @FXML
    private TableColumn<?, ?> colProductQty;

    @FXML
    private TableColumn<?, ?> colSupplierEmail;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private JFXTextField txtProductQty;

    @FXML
    private JFXTextField txtSupplierEmail;

    @FXML
    private JFXTextField txtSupplierId;

    @FXML
    private JFXTextField txtSupplierName;


    SupplierService service = new SupplierController();

  List<Supplier> supplierList =   new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colSupplierEmail.setCellValueFactory(new PropertyValueFactory<>("supplierEmail"));
        colProduct.setCellValueFactory(new PropertyValueFactory<>("product"));
        colProductQty.setCellValueFactory(new PropertyValueFactory<>("productQty"));


        loadTable();

        ObservableList<String> productList = FXCollections.observableArrayList();
        productList.add("Shirts");
        productList.add("trousers");
        productList.add("shorts");
        productList.add("blouse");

        cmbProduct.setItems(productList);



        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {

           //after delete new value will null so want remove null point exception use this code
            if(newVal!=null){

                addValueToText(newVal);


            }






        });

    }


    private void addValueToText(Supplier newVal) {

        ObservableList<String> productList = FXCollections.observableArrayList();
        txtSupplierId.setText(newVal.getSupplierId());
        txtSupplierName.setText(newVal.getSupplierName());
        txtSupplierEmail.setText(newVal.getSupplierEmail());
        cmbProduct.setValue(newVal.getProduct());
        txtProductQty.setText(""+newVal.getProductQty());


    }


    @FXML
    void btnAddOnAction(ActionEvent event) {

        String supplierId = txtSupplierId.getText();
        String supplierName = txtSupplierName.getText();
        String supplierEmail = txtSupplierEmail.getText();
        String product = cmbProduct.getValue();
        int productQty = Integer.parseInt(txtProductQty.getText());


        Supplier supplier = new Supplier(supplierId, supplierName, supplierEmail, product, productQty);


        if(service.addSupplier(supplier)){

            new Alert(Alert.AlertType.INFORMATION,"Supplier Added!!").show();
            loadTable();


        }

        else {


            new Alert(Alert.AlertType.ERROR,"Supplier Not Added!!").show();


        }




    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        if(service.deleteSupplier(txtSupplierId.getText())){

            new Alert(Alert.AlertType.INFORMATION,"Supplier Deleted!!").show();
            loadTable();


        }

        else {


            new Alert(Alert.AlertType.ERROR,"Supplier Not Deleted!!").show();


        }






    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
      loadTable();

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        Supplier supplier = service.searchSupplier(txtSupplierId.getText());

        txtSupplierId.setText(supplier.getSupplierId());
        txtSupplierName.setText(supplier.getSupplierName());
        txtSupplierEmail.setText(supplier.getSupplierEmail());
        cmbProduct.setValue(supplier.getProduct());
        txtProductQty.setText(""+supplier.getProductQty());


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String supplierId = txtSupplierId.getText();
        String supplierName = txtSupplierName.getText();
        String supplierEmail = txtSupplierEmail.getText();
        String product = cmbProduct.getValue();
        int productQty = Integer.parseInt(txtProductQty.getText());


        Supplier supplier = new Supplier(supplierId, supplierName, supplierEmail, product, productQty);

        if(service.updateSupplier(supplier)){

            new Alert(Alert.AlertType.INFORMATION,"Supplier Updated!!").show();
            loadTable();


        }

        else {


            new Alert(Alert.AlertType.ERROR,"Supplier Not Updated!!").show();


        }








    }



    private void loadTable(){



        tblSupplier.setItems(service.getSupplier());







    }


}
