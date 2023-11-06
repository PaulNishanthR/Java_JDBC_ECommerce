package com.codewithnishanth.views;

import com.codewithnishanth.models.Order;
import com.codewithnishanth.models.User;
import com.codewithnishanth.utils.StringUtil;

import java.util.ArrayList;

import static com.codewithnishanth.utils.AppOutput.println;
import static com.codewithnishanth.utils.UserUtil.getLoggedInUser;

public class OrderPage {
    public void printOrders(ArrayList<Order> orders) {
        println(StringUtil.ORDER_MESSAGE);

        User user = getLoggedInUser();
        for(Order order:orders)
        {
            if(order.getUser().getId()==user.getId())
            {
                println(order.getId()+". " + order.getProduct().getProductName() +", "+ order.getDate() +", ₹."+order.getProduct().getPrice());
            }
        }
    }
}
