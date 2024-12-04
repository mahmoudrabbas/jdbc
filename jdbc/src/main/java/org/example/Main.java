package org.example;


import org.example.dao.EmployeeFun;

public class Main {
    public static void main(String[] args) {
       new EmployeeFun().findAll().forEach(System.out::println);
    }
}