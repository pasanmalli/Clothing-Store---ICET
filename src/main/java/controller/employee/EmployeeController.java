package controller.employee;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Employee;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeController implements EmployeeService {


    @Override
    public boolean addEmployee(Employee employee) {

        boolean isAdd;
        try {

            String SQL = "INSERT INTO employee VALUES(?,?,?,?)";
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement psTm = connection.prepareStatement(SQL);

            psTm.setObject(1,employee.getEmpId());
            psTm.setObject(2,employee.getEmpName());
            psTm.setObject(3,employee.getEmpEmail());
            psTm.setObject(4,employee.getJoinDate());

            isAdd = psTm.executeUpdate() > 0;

            //System.out.println(isAdd);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(isAdd){

            new Alert(Alert.AlertType.INFORMATION,"Employer Added!!").show();


        }

        return false;
    }

    @Override
    public ObservableList<Employee> getAllEmployees() {

        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement("SELECT * FROM employee");

            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()){

                employeeObservableList.add(

                   new Employee(
                        resultSet.getString("empid")
                        , resultSet.getString("emp_name")
                        , resultSet.getString("emp_email")
                        , resultSet.getDate("join_date").toLocalDate()

                ));






            }

            return employeeObservableList;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public boolean updateEmployee(Employee employee) {
        boolean isUpdate;
        String SQL = "UPDATE employee SET emp_name=?, emp_email=?, join_date=? WHERE empid=?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,employee.getEmpName());
            psTm.setObject(2,employee.getEmpEmail());
            psTm.setObject(3,employee.getJoinDate());
            psTm.setObject(4,employee.getEmpId());

            isUpdate = psTm.executeUpdate() > 0;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (isUpdate){

           return true;

        }

        return false;
    }

    @Override
    public boolean deleteEmployee(String id) {

        boolean isDeleted;

        try {


           isDeleted = DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM employee WHERE empid = '" +id+ "'") > 0;

            if (isDeleted){

                new Alert(Alert.AlertType.INFORMATION,"Employee Deleted!!").show();


            }

            else{

                new Alert(Alert.AlertType.ERROR,"Employee Not Deleted!!").show();


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(isDeleted){

          return true;
        }

        return false;
    }

    @Override
    public Employee searchEmployee(String empId) {

        String SQL="SELECT * FROM employee WHERE empid='"+empId+"'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()){
                return new Employee(
                        resultSet.getString(1)
                        ,resultSet.getString(2)
                        ,resultSet.getString(3)
                        ,resultSet.getDate(4).toLocalDate()
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;}


    }

