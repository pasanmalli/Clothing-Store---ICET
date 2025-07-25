package controller.employee;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import model.Item;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    @FXML
    private TableColumn colEmployeeEmail;

    @FXML
    private TableColumn colEmployeeId;



    @FXML
    private TableColumn colEmployeeName;

    @FXML
    private TableColumn colJoinedDate;

    @FXML
    private TableView tblEmployees;

    @FXML
    private JFXTextField txtEmployeeEmail;

    @FXML
    private JFXTextField txtEmployeeId;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private DatePicker dateJoinedDate;

    List<Employee> employeeList = new ArrayList<>();

    EmployeeService service = new EmployeeController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("empEmail"));
        colJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinDate"));



        loadTable();



        tblEmployees.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {


            addValueToText((Employee) newVal);


        });

    }

    private void addValueToText(Employee newVal) {

        txtEmployeeId.setText(newVal.getEmpId());
        txtEmployeeName.setText(newVal.getEmpName());
        txtEmployeeEmail.setText(newVal.getEmpEmail());
        dateJoinedDate.setValue(newVal.getJoinDate());


    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {

        String employeeId = txtEmployeeId.getText();
        String employeeName = txtEmployeeName.getText();
        String employeeEmail= txtEmployeeEmail.getText();
        LocalDate joinedDate = dateJoinedDate.getValue();





        Employee employee = new Employee(employeeId, employeeName, employeeEmail, joinedDate);



       if (service.addEmployee(employee)){


           new Alert(Alert.AlertType.INFORMATION,"Employee Added!!").show();
           loadTable();

       }

       else {

           new Alert(Alert.AlertType.ERROR,"Employee Not Added!!").show();


       }






        //System.out.println(employee);


        //employeeList.add(employee);



    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

       if (service.deleteEmployee(txtEmployeeId.getText()))
       {

           new Alert(Alert.AlertType.INFORMATION,"Employee Deleted!!").show();
           loadTable();


       }

        else {

           new Alert(Alert.AlertType.ERROR,"Employee Not Deleted!!").show();


       }



    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        Employee employee = service.searchEmployee(txtEmployeeId.getText());

        txtEmployeeName.setText(employee.getEmpName());
        txtEmployeeEmail.setText(employee.getEmpEmail());
        dateJoinedDate.setValue(employee.getJoinDate());


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String employeeId = txtEmployeeId.getText();
        String employeeName = txtEmployeeName.getText();
        String employeeEmail= txtEmployeeEmail.getText();
        LocalDate joinedDate = dateJoinedDate.getValue();





        Employee employee = new Employee(employeeId, employeeName, employeeEmail, joinedDate);



        if (service.updateEmployee(employee)){


            new Alert(Alert.AlertType.INFORMATION,"Employee Updated!!").show();
            loadTable();

        }

        else {

            new Alert(Alert.AlertType.ERROR,"Employee Not Updated!!").show();


        }


    }

    public void btnReloadOnAction(ActionEvent actionEvent) {

        loadTable();

    }


    private void loadTable(){






        tblEmployees.setItems(service.getAllEmployees());







    }


}

