package com.codewithnishanth.controllers;

import com.codewithnishanth.dao.CategoryDao;
import com.codewithnishanth.dao.ProductDao;
import com.codewithnishanth.models.Category;
import com.codewithnishanth.models.Product;
import com.codewithnishanth.utils.AppException;
import com.codewithnishanth.utils.StringUtil;
import com.codewithnishanth.views.ProductPage;

import java.util.ArrayList;

import static com.codewithnishanth.utils.AppInput.enterInteger;
import static com.codewithnishanth.utils.AppOutput.println;

public class ProductController {


    private final ProductPage productPage;
    private final CategoryDao categoryDao;
    private final ProductDao productDao;
    private final HomeController homeController;
    private final CartController cartController;

    public ProductController(HomeController homeController,CartController cartController) {
        this.productPage = new ProductPage();
        this.categoryDao = new CategoryDao();
        this.productDao = new ProductDao();
        this.homeController = homeController;
        this.cartController = cartController;
    }

    private static ArrayList<Category> categories = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    public void printCategories() {
        categories = categoryDao.getCategories();
        products = productDao.getProducts();
        productPage.printCategories(categories);
        int choice;
        try {
            choice = enterInteger(StringUtil.CHOICE);
            if(choice!=100)
            {

                boolean isCategory = false;
                for(Category category : categories)
                {
                    if(category.getId()==choice)
                    {
                        isCategory=true;break;
                    }
                }
                if(isCategory)
                {
                    productPage.printCategoryProducts(choice, products);
                    productsChoice();
                }
                else
                {
                    productPage.printAllProducts(products);
                    productsChoice();
                }

            }
            else if(choice==100)
            {
                homeController.printMenu();
            }
            else
            {
                invalidException(new AppException(StringUtil.INVALID_CHOICE));
            }
        } catch (AppException e) {
            invalidException(e);
        }

    }

    private void productsChoice() {
        println(StringUtil.STATIC_CHOICES);
        println(StringUtil.PRODUCT_OPTIONS);
        int productChoice;
        try {
            productChoice = enterInteger(StringUtil.CHOICE);
            if(productChoice==1)
            {
                cartController.AddtoCart();
            }
            else if(productChoice==100)
            {
                homeController.printMenu();
            }
            else
            {
                invalidProductException(new AppException(StringUtil.INVALID_CHOICE));
            }
        } catch (AppException e) {
            invalidProductException(e);
        }

    }

    private void invalidProductException(AppException e) {
        println(e.getMessage());
        productsChoice();
    }
    private void invalidException(AppException e) {
        println(e.getMessage());
        printCategories();
    }

    public void printProducts() {
        products = productDao.getProducts();
        productPage.printAllProducts(products);
        productsChoice();
    }
}
