package org.example.dao;

import org.example.model.Employee;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class EmployeeFun implements EmployeeDao{
    Connection connection = JDBC.getConnection();
    @Override
    public List<Employee> findAll() {
        List<Employee> list = new LinkedList<Employee>();
        String query = "SELECT * FROM employee;";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ResultSet res = ps.executeQuery();
            while (res.next()){
//                list.add(new Employee(res.getInt("id"), res.getString("name"), res.getBoolean("gender"), res.getDate("birth_date"), res.getDouble("salary")));
                list.add(Employee.builder().id(res.getInt("id")).birth_date(res.getDate("birth_date")).name(res.getString("name")).salary(res.getDouble("salary")).gender(res.getBoolean("gender")).build());
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = null;

        String query = "SELECT * FROM employee WHERE id=?";

        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            if(res.next()){
                employee = new Employee(res.getInt("id"), res.getString("name"), res.getBoolean("gender"), res.getDate("birth_date"), res.getDouble("salary"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return employee;
    }

    @Override
    public void save(Employee employee) {

        if(connection==null) return;


        if(employee.getId() == 0){
            String query = "INSERT INTO Employee(name, gender, birth_date, salary) VALUES (?, ?, ?, ?);";
            try(PreparedStatement ps = connection.prepareStatement(query)){
                ps.setString(1, employee.getName());
                ps.setBoolean(2, employee.isGender());
                ps.setDate(3, new Date(employee.getBirth_date().getTime()));
                ps.setDouble(4, employee.getSalary());

                ps.executeUpdate();

            }catch (SQLException se){
                se.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // update
            String query = "UPDATE employee SET name=?, gender=?, birth_date=?, salary=? WHERE id=?";

            try(PreparedStatement ps = connection.prepareStatement(query)){
                ps.setString(1, employee.getName());
                ps.setBoolean(2, employee.isGender());
                ps.setDate(3, new java.sql.Date(employee.getBirth_date().getTime()));
                ps.setDouble(4, employee.getSalary());
                ps.setInt(5, employee.getId());


                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    @Override
    public void deleteById(int id) {

        String query = "DELETE FROM employee WHERE id=?";

        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (Exception se){
            se.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
