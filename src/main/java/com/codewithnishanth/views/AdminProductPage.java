package com.codewithnishanth.views;

import com.codewithnishanth.models.Product;
import com.codewithnishanth.utils.StringUtil;

import java.util.ArrayList;

import static com.codewithnishanth.utils.AppOutput.println;

public class AdminProductPage {
    public void viewProducts(ArrayList<Product> products) {
        println(StringUtil.PRODUCT_MESSAGE);
        for (Product product : products) {

            println(product.getId() + ". " + product.getProductName() + ", ₹." + product.getPrice());

        }
    }

    public void productChoices() {
        println(StringUtil.ADMIN_PRODUCT_CHOICE);
    }
}
