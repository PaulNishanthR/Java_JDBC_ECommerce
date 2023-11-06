package com.codewithnishanth.controllers;

import com.codewithnishanth.dao.CartDao;
import com.codewithnishanth.dao.OrderDao;
import com.codewithnishanth.models.Cart;
import com.codewithnishanth.models.Order;
import com.codewithnishanth.models.User;
import com.codewithnishanth.utils.AppException;
import com.codewithnishanth.utils.StringUtil;
import com.codewithnishanth.views.OrderPage;

import java.util.ArrayList;

import static com.codewithnishanth.utils.AppInput.enterInteger;
import static com.codewithnishanth.utils.AppOutput.println;
import static com.codewithnishanth.utils.UserUtil.getLoggedInUser;

public class OrderController {

    private final OrderPage orderPage;
    private final OrderDao orderDao;

    private final CartDao cartDao;

    private final HomeController homeController;

    public OrderController(HomeController homeController) {
        this.orderPage = new OrderPage();
        this.orderDao = new OrderDao();
        this.cartDao = new CartDao();
        this.homeController= homeController;
    }

    private static ArrayList<Cart> carts = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();
    public void orderedItems() {
        User user = getLoggedInUser();
        carts=orderDao.getUserCartItems(user.getId());
        orderDao.AddUserOrders(carts);
        cartDao.updateCart(user.getId());
        println(StringUtil.ORDER_SUCCESSFUL);
    }

    public void OrderedItemsList() {
        User user = getLoggedInUser();
        orders=orderDao.getOrders(user.getId());
        if(!orders.isEmpty())
        {
            orderPage.printOrders(orders);
            println(StringUtil.BACK);
            int choice;
            try {
                choice = enterInteger(StringUtil.CHOICE);
                if (choice == 100) {
                    homeController.printMenu();
                } else {
                    invalidException(new AppException(StringUtil.INVALID_CHOICE));
                }
            } catch (AppException e) {
                invalidException(e);
            }
        }
        else
        {
            println(StringUtil.ORDER_EMPTY);
            homeController.printMenu();
        }

    }
    private void invalidException(AppException e) {
        println(e.getMessage());
        OrderedItemsList();
    }
}
