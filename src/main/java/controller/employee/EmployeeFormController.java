package controller.employee;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("empEmail"));
        colJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinDate"));

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String employeeId = txtEmployeeId.getText();
        String employeeName = txtEmployeeName.getText();
        String employeeEmail= txtEmployeeEmail.getText();
        LocalDate joinedDate = dateJoinedDate.getValue();


        Employee employee = new Employee(employeeId, employeeName, employeeEmail, joinedDate);

        System.out.println(employee);


        employeeList.add(employee);



    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    public void btnReloadOnAction(ActionEvent actionEvent) {

        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();

        employeeList.forEach(employee ->{

            employeeObservableList.add(employee);




        });


        tblEmployees.setItems(employeeObservableList);


    }


}
