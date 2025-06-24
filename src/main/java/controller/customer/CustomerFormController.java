package controller.customer;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbTitle;

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colDob;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colSalary;


    @FXML
    private TableColumn colPostalCode;

    @FXML
    private TableColumn colProvince;

    @FXML
    private TableColumn colCity;

    @FXML
    private DatePicker dateDob;

    @FXML
    private TableView tblCustomer;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtProvince;

  List<Customer> customerList = new ArrayList<>();

    CustomerService service =new CustomerController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postal_code"));



        loadTable();

        ObservableList<String> titleList = FXCollections.observableArrayList();
        titleList.add("MR.");
        titleList.add("MISS.");
        titleList.add("MRS.");

        cmbTitle.setItems(titleList);


        //----------------------------------------------------------------

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newVal) ->{

            if (newVal!=null) {
                addValueToText((Customer) newVal);
            }


        } );



    }

    private void addValueToText(Customer newVal) {
        txtId.setText(newVal.getId());
        txtName.setText(newVal.getName());
        txtAddress.setText(newVal.getAddress());
        dateDob.setValue(newVal.getDob());
        txtSalary.setText(""+ newVal.getSalary());
        txtCity.setText(newVal.getCity());
        txtProvince.setText(newVal.getProvince());
        txtPostalCode.setText(newVal.getPostal_code());







    }


    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = cmbTitle.getValue() + txtName.getText();
        String address = txtAddress.getText();
        Double salary = Double.parseDouble(txtSalary.getText());
        String title = cmbTitle.getValue();
        LocalDate dob = dateDob.getValue();
        String province = txtProvince.getText();
        String postal_code = txtPostalCode.getText();
        String city = txtCity.getText();



        Customer customer = new Customer(id,title,name,dob,address, salary,city,province,postal_code);

        if(service.addCustomer(customer)){
            new Alert(Alert.AlertType.INFORMATION).show();
        }
        else {

            new Alert(Alert.AlertType.ERROR).show();

        }




    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        service.deleteCustomer(txtId.getText());


    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {


        loadTable();



    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        service.serchCustomer(txtId.getText());

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String id = txtId.getText();
        String name = cmbTitle.getValue() + txtName.getText();
        String address = txtAddress.getText();
        Double salary = Double.parseDouble(txtSalary.getText());
        String title = cmbTitle.getValue();
        LocalDate dob = dateDob.getValue();
        String province = txtProvince.getText();
        String postal_code = txtPostalCode.getText();
        String city = txtCity.getText();



        Customer customer = new Customer(id,title,name,dob,address, salary,city,province,postal_code);

        if (service.updateCustomer(customer)){

            new Alert(Alert.AlertType.INFORMATION).show();

        }

        else {

            new Alert(Alert.AlertType.ERROR).show();


        }



    }


    private void loadTable(){

        ObservableList<Customer> customerObservableList  = FXCollections.observableArrayList();
        try {


            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement("SELECT * FROM customer");
            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()){
                Customer customer = new Customer(
                        resultSet.getString("id")
                        ,resultSet.getString("title")
                        , resultSet.getString("name")
                        , resultSet.getDate("dob").toLocalDate()
                        , resultSet.getString("address")
                        , resultSet.getDouble("salary")
                        ,resultSet.getString("city")
                        ,resultSet.getString("province")
                        ,resultSet.getString("postal_code")


                );

                //System.out.println(customer);
                customerObservableList.add(customer);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // -----------------------------------------------------------------------


        customerList.forEach(customer -> {
            customerObservableList.add(customer);
        });


        tblCustomer.setItems(customerObservableList);





    }



}
