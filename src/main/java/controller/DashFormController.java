package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class DashFormController {

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {

        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    @FXML
    void btnViewCustomerOnAction(ActionEvent event) {

        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/item_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnSupplierOnAction(ActionEvent actionEvent) {

        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/supplier_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
