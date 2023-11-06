package com.codewithnishanth.controllers;

import com.codewithnishanth.dao.CategoryDao;
import com.codewithnishanth.models.Category;
import com.codewithnishanth.utils.AppException;
import com.codewithnishanth.utils.StringUtil;
import com.codewithnishanth.views.AdminCategoryPage;

import java.util.ArrayList;

import static com.codewithnishanth.utils.AppInput.enterInteger;
import static com.codewithnishanth.utils.AppInput.enterString;
import static com.codewithnishanth.utils.AppOutput.println;

public class AdminCategoryController {


    private final AdminCategoryPage adminCategoryPage;
    private final CategoryDao categoryDao;

    private final AdminController adminController;
    public AdminCategoryController(AdminController adminController) {
        this.adminCategoryPage = new AdminCategoryPage();
        this.categoryDao = new CategoryDao();
        this.adminController = adminController;

    }

    private static ArrayList<Category> categories = new ArrayList<>();
    public void viewCategories() {
        categories = categoryDao.getCategories();
        adminCategoryPage.printCategories(categories);
        println(StringUtil.STATIC_CHOICES);
        adminCategoryPage.categoryChoices();
        int choice;
        try {
            choice = enterInteger(StringUtil.CHOICE);
            if (choice == 1) {
                AddCategory();
            } else if (choice == 2) {
                DeleteCategory();
            } else if (choice == 100) {
                adminController.printMenu();
            } else {
                invalidException(new AppException(StringUtil.INVALID_CHOICE));
            }

        } catch (AppException e) {
            invalidException(e);

        }
    }

    private void DeleteCategory() {

        categories = categoryDao.getCategories();
        int categoryId;
        try {
            categoryId = enterInteger(StringUtil.CATEGORY_ID);
            boolean isCategoryId = false;
            for(Category category:categories)
            {
                if(category.getId()==categoryId)
                {
                    isCategoryId=true;break;
                }
            }
            if(isCategoryId)
            {
                categoryDao.deleteCategory(categoryId);
                println(StringUtil.CATEGORY_DELETED_MESSAGE);
                adminController.printMenu();
            }
            else
            {
                invalidException(new AppException(StringUtil.INVALID_CHOICE));
            }

        } catch (AppException e) {
            throw new RuntimeException(e);
        }
    }

    private void AddCategory() {
        String categoryName;
        categoryName = enterString(StringUtil.ENTER_CATEGORY_NAME);
        categoryDao.addCategory(categoryName);
        println(StringUtil.CATEGORY_SUCCESSFUL);
        adminController.printMenu();
    }

    private void invalidException(AppException e) {
        println(e.getMessage());
        adminCategoryPage.categoryChoices();
    }
}
