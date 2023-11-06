package com.codewithnishanth.utils;

public class QueryUtil {
    public static final String SELECT_USER = "SELECT users.id,users.email,users.userPassword,users.roleId,roles.roleName FROM users INNER JOIN roles ON users.roleId=roles.id WHERE users.email=? AND users.userPassword=?;";
    public static final String SELECT_ALL_USERS = "SELECT email FROM users;";
    public static final String REGISTER_USER = "INSERT INTO users(email,userPassword,roleId) VALUES(?,?,?);";
    public static final String SELECT_CATEGORY = "SELECT id,categoryName FROM category WHERE delete_flag=FALSE;";
    public static final String SELECT_PRODUCTS = "SELECT product.id,product.productName,product.price,product.categoryId,category.categoryName FROM product INNER JOIN category ON product.categoryId = category.id WHERE product.delete_flag=FALSE;";
    public static final String SELECT_USER_CART = "SELECT cart.id,cart.userId,cart.productId,cart.count,cart.isOrdered,users.email,users.userPassword,product.id as productId,product.productName,product.price,product.categoryId,category.categoryName FROM cart INNER JOIN users ON cart.userId=users.id INNER JOIN product ON cart.productId= product.id INNER JOIN category ON category.id= product.categoryId WHERE userId=? AND cart.isOrdered=FALSE;";
    public static final String INSERT_CART_ITEM = "INSERT INTO cart(userId,productId,count) VALUES(?,?,?);";
    public static final String UPDATE_CART_ITEM = "UPDATE cart SET count=? WHERE userId=? AND productId=? AND isOrdered=FALSE;";
    public static final String SELECT_ITEM_COUNT = "SELECT count FROM cart WHERE userId=? AND productId=? AND isOrdered=FALSE;";

    public static final String INSERT_ORDER_ITEM = "INSERT INTO orders(userId,productId,statusId) VALUES(?,?,?);";
    public static final String UPDATE_CART_ORDER = "UPDATE cart SET isOrdered=TRUE WHERE userId=?;";

    public static final String SELECT_USER_ORDER = "SELECT orders.id,orders.userId,orders.productId,orders.date,cart.count,users.email,users.userPassword,product.productName,product.price,product.categoryId,category.categoryName,statusDetail.orderStatus FROM orders INNER JOIN users ON orders.userId=users.id INNER JOIN product ON orders.productId= product.id INNER JOIN category ON category.id= product.categoryId INNER JOIN cart ON cart.userId=orders.userId AND cart.productId = orders.productId INNER JOIN statusDetail ON orders.statusId=statusDetail.id WHERE orders.userId=?;";

    public static final String SELECT_ALL_ORDERS = "SELECT orders.id,orders.userId,orders.productId,orders.date,cart.count,users.email,users.userPassword,product.productName,product.price,product.categoryId,category.categoryName,statusDetail.orderStatus FROM orders INNER JOIN users ON orders.userId=users.id INNER JOIN product ON orders.productId= product.id INNER JOIN category ON category.id= product.categoryId INNER JOIN cart ON cart.userId=orders.userId AND cart.productId = orders.productId INNER JOIN statusDetail ON orders.statusId=statusDetail.id;";


    public static final String INSERT_CATEGORY = "INSERT INTO category(categoryName) VALUES (?);";
    public static final String DELETE_CATEGORY = "UPDATE category SET delete_flag=TRUE WHERE id=?;";
    public static final String SELECT_CATEGORY_ID = "SELECT id FROM category WHERE categoryName=?;";
    public static final String INSERT_PRODUCT = "INSERT INTO product(productName,price,categoryId) VALUES(?,?,?);";
    public static final String DELETE_PRODUCT = "UPDATE product SET delete_flag=TRUE WHERE id=?;";
    public static final String UPDATE_PRODUCT = "UPDATE product SET productName=?,price=? WHERE id=?;";
}
