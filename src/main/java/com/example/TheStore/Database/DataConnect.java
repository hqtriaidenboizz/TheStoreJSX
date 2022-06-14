package com.example.TheStore.Database;

import com.example.TheStore.Models.Products;

import java.sql.*;
import java.util.ArrayList;

public class DataConnect {
    private Connection connection;

    public static final String URL = "jdbc:mysql://localhost/store";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public DataConnect(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connect successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Products> getProducts(){
        ArrayList<Products> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try {
            ResultSet results = connection.prepareStatement(sql).executeQuery();
            while (results.next()){
                Products products = new Products(
                        results.getInt("id"),
                        results.getString("name"),
                        results.getInt("price"),
                        results.getInt("quantity")
                );
                list.add(products);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public void insertProducts(Products products){
        String sql = "INSERT INTO products (name, price, quantity) VALUE ('"+products.name+"','"+products.price+"','"+products.quantity+"')";
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProducts(Products products){
        String sql = "UPDATE products SET name = '"+ products.name +"', price = '"+products.price+"', quantity = '"+products.quantity+"' WHERE id = "+ products.id;
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProducts(int id){
        String sql = "DELETE FROM products WHERE id = "+ id;
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
