package com.codewithnishanth.controllers;

import com.codewithnishanth.dao.CategoryDao;
import com.codewithnishanth.dao.ProductDao;
import com.codewithnishanth.models.Category;
import com.codewithnishanth.models.Product;
import com.codewithnishanth.utils.AppException;
import com.codewithnishanth.utils.StringUtil;
import com.codewithnishanth.views.AdminProductPage;

import java.util.ArrayList;

import static com.codewithnishanth.utils.AppInput.*;
import static com.codewithnishanth.utils.AppOutput.println;

public class AdminProductController {

    private final AdminController adminController;
    private final AdminProductPage adminProductPage;
    private final ProductDao productDao;
    private final CategoryDao categoryDao;
    public AdminProductController(AdminController adminController) {
        this.adminController = adminController;
        this.adminProductPage = new AdminProductPage();
        this.productDao = new ProductDao();
        this.categoryDao = new CategoryDao();
    }

    private static ArrayList<Product> products = new ArrayList<>();
    public void viewProducts() {
        products = productDao.getProducts();
        adminProductPage.viewProducts(products);
        println(StringUtil.STATIC_CHOICES);
        adminProductPage.productChoices();
        int choice;
        try {
            choice = enterInteger(StringUtil.CHOICE);
            if (choice == 1) {
                AddProduct();
            } else if (choice == 2) {
                EditProduct();
            } else if (choice == 3) {
                DeleteProduct();
            } else if (choice == 100) {
                adminController.printMenu();
            } else {
                invalidException(new AppException(StringUtil.INVALID_CHOICE));
            }
        } catch (
                AppException e) {
            invalidException(e);

        }

    }

    private void DeleteProduct() {
        int productId;
        try {
            productId = enterInteger(StringUtil.ENTER_PRODUCT_DELETE);
            boolean isProduct = false;
            for(Product product:productDao.getProducts())
            {
                if(product.getId()==productId)
                {
                    isProduct=true;
                }
            }
            if(isProduct)
            {
                productDao.deleteProduct(productId);
                println(StringUtil.PRODUCT_DELETED_SUCCESSFULLY);
                viewProducts();
            }
            else
            {
                invalidException(new AppException(StringUtil.INVALID_PRODUCT));
            }
        } catch (AppException e) {
            invalidException(e);
        }
    }

    private void EditProduct() {
        int productId;
        try {
            productId = enterInteger(StringUtil.ENTER_PRODUCT_EDIT);
            boolean isProduct = false;
            for(Product product:productDao.getProducts())
            {
                if(product.getId()==productId)
                {
                    isProduct=true;
                }
            }
            if(isProduct)
            {
                String productName;
                double price = 0;
                productName = enterString(StringUtil.ENTER_PRODUCT_NAME_EDITED);
                try {
                    price = enterDouble(StringUtil.ENTER_PRODUCT_PRICE_EDITED);
                    if(price<=0)
                    {
                        println(StringUtil.PRICE_ERROR);
                        EditProduct();
                    }
                } catch (AppException e) {
                    invalidException(e);
                }
                productDao.updateProduct(productId, productName, price);
                println(StringUtil.PRODUCT_EDITED_SUCCESSFULLY);
                viewProducts();
            }
            else
            {
                invalidException(new AppException(StringUtil.INVALID_PRODUCT));
            }
        } catch (AppException e) {
            invalidException(e);
        }
    }

    private void AddProduct() {
        String categoryName, productName;
        double price = 0;
        categoryName = enterString(StringUtil.ENTER_CATEGORY_NAME);
        boolean isCategory = false;
        for(Category category:categoryDao.getCategories())
        {
            if(category.getCategoryName().equals(categoryName))
            {
                isCategory=true;break;
            }
        }
        if(!isCategory)
        {
            println(StringUtil.CATEGORY_ERROR);
            AddProduct();
        }
        productName = enterString(StringUtil.ENTER_PRODUCT_NAME);
        try {
            price = enterDouble(StringUtil.ENTER_PRODUCT_PRICE);
            if(price<=0)
            {
                println(StringUtil.PRICE_ERROR);
                AddProduct();
            }
        } catch (AppException e) {
            invalidException(e);
        }
        productDao.AddProduct(categoryName,productName,price);
        println(StringUtil.PRODUCT_SUCCESSFUL);
        adminController.printMenu();

    }

    private void invalidException(AppException e) {
        println(e.getMessage());
        viewProducts();
    }
}
