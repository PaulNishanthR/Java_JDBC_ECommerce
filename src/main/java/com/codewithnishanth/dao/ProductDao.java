package com.codewithnishanth.dao;

import com.codewithnishanth.db.DbConnection;
import com.codewithnishanth.models.Category;
import com.codewithnishanth.models.Product;
import com.codewithnishanth.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDao {

    private final Connection connection;

    public ProductDao() {
        this.connection = DbConnection.getConnection();
    }
    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.SELECT_PRODUCTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Category category = new Category(Integer.parseInt(resultSet.getString("categoryId")),resultSet.getString("categoryName"));
                Product product = new Product(Integer.parseInt(resultSet.getString("id")),category,resultSet.getString("productName"),Double.parseDouble(resultSet.getString("price")));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }


    public void AddProduct(String categoryName, String productName, double price) {
        int categoryId = getCategoryId(categoryName);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.INSERT_PRODUCT);
            preparedStatement.setString(1,productName);
            preparedStatement.setDouble(2,price);
            preparedStatement.setInt(3,categoryId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getCategoryId(String categoryName) {
        int categoryId = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.SELECT_CATEGORY_ID);
            preparedStatement.setString(1,categoryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                categoryId = Integer.parseInt(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryId;
    }

    public void deleteProduct(int productId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.DELETE_PRODUCT);
            preparedStatement.setInt(1,productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(int productId, String productName, double price) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.UPDATE_PRODUCT);
            preparedStatement.setString(1,productName);
            preparedStatement.setDouble(2,price);
            preparedStatement.setInt(3,productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
