package com.codewithnishanth.dao;

import com.codewithnishanth.db.DbConnection;
import com.codewithnishanth.models.*;
import com.codewithnishanth.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static com.codewithnishanth.utils.UserUtil.getLoggedInUser;

public class OrderDao {

    private final Connection connection;

    public OrderDao() {
        this.connection = DbConnection.getConnection();
    }
    public ArrayList<Cart> getUserCartItems(int userId) {
        ArrayList<Cart> carts = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.SELECT_USER_CART);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Category category = new Category(Integer.parseInt(resultSet.getString("categoryId")),resultSet.getString("categoryName"));
                Product product = new Product(Integer.parseInt(resultSet.getString("productId")),category,resultSet.getString("productName"),Double.parseDouble(resultSet.getString("price")));
                Cart cart = new Cart(Integer.parseInt(resultSet.getString("id")),getLoggedInUser(),product,Integer.parseInt(resultSet.getString("count")));
                carts.add(cart);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carts;
    }

    public void AddUserOrders(ArrayList<Cart> carts) {

        for(Cart cart:carts)
        {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.INSERT_ORDER_ITEM);
                preparedStatement.setInt(1,cart.getUser().getId());
                preparedStatement.setInt(2,cart.getProduct().getId());
                preparedStatement.setInt(3,1);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }


    public ArrayList<Order> getOrders(int userId) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.SELECT_USER_ORDER);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Category category = new Category(Integer.parseInt(resultSet.getString("categoryId")),resultSet.getString("categoryName"));
                Product product = new Product(Integer.parseInt(resultSet.getString("productId")),category,resultSet.getString("productName"),Double.parseDouble(resultSet.getString("price")));
                Date date = resultSet.getDate("date");
                String status = resultSet.getString("orderStatus");
                Order order = new Order(Integer.parseInt(resultSet.getString("id")),getLoggedInUser(),date,status,product);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.SELECT_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Category category = new Category(Integer.parseInt(resultSet.getString("categoryId")),resultSet.getString("categoryName"));
                Product product = new Product(Integer.parseInt(resultSet.getString("productId")),category,resultSet.getString("productName"),Double.parseDouble(resultSet.getString("price")));
                Date date = resultSet.getDate("date");
                String status = resultSet.getString("orderStatus");
                Role role = new Role(2,"User");
                User user = new User(Integer.parseInt(resultSet.getString("userId")),resultSet.getString("email"),resultSet.getString("userPassword"),role);
                Order order = new Order(Integer.parseInt(resultSet.getString("id")),user,date,status,product);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }
}
