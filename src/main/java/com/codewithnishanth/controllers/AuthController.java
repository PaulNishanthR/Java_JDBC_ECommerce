package com.codewithnishanth.controllers;

import com.codewithnishanth.dao.UserDao;
import com.codewithnishanth.models.User;
import com.codewithnishanth.utils.AppException;
import com.codewithnishanth.utils.StringUtil;
import com.codewithnishanth.utils.UserUtil;
import com.codewithnishanth.views.AuthPage;
import com.codewithnishanth.views.LoginPage;
import com.codewithnishanth.views.RegisterPage;

import static com.codewithnishanth.utils.AppInput.enterInteger;
import static com.codewithnishanth.utils.AppInput.enterString;
import static com.codewithnishanth.utils.AppOutput.println;

public class AuthController {

    private final AuthPage authPage;
    private final LoginPage loginPage;
    private final RegisterPage registerPage;

    private final HomeController homeController;

    private final UserDao userDao;
    private final AdminController adminController;

    public AuthController() {
        this.authPage = new AuthPage();
        this.loginPage = new LoginPage();
        this.registerPage = new RegisterPage();
        this.userDao = new UserDao();
        this.homeController = new HomeController();
        this.adminController = new AdminController();
    }

    public void authMenu() {
        authPage.authMenu();
        int choice;
        try {
            choice = enterInteger(StringUtil.CHOICE);
            if (choice == 1) {
                login();
            } else if (choice == 2) {
                register();
            } else {
                invalidException(new AppException(StringUtil.INVALID_CHOICE));
            }
        } catch (AppException e) {
            invalidException(e);

        }
    }

    private void register() {
        String  email, password, confirm_password;
        email = enterString(StringUtil.ENTER_EMAIL);
        password = enterString(StringUtil.ENTER_PASSWORD);
        confirm_password = enterString(StringUtil.ENTER_CONFIRM_PASSWORD);
        if (password.equals(confirm_password))
        {
            boolean isExistingUser = userDao.getUser(email);
            if(!isExistingUser)
            {
                userDao.registerUser(email,password);
                registerPage.registrationSuccessful();
                authMenu();

            }
            else
            {
                registerPage.userExists();
                authMenu();
            }

        }
        else
        {
            registerPage.passwordMisMatch();
            authMenu();
        }

    }

    private void login() {
        String email = enterString(StringUtil.ENTER_EMAIL);
        String password = enterString(StringUtil.ENTER_PASSWORD);

        User user = userDao.validateUser(email, password);

        if (user!=null) {
            if(user.getRole().getRole().equals("Admin"))
            {
                adminController.welcome();
            }
            else
            {
                loginPage.loginSuccess();
                UserUtil.setLoggedInUser(user);
                homeController.printMenu();
            }


        } else {
            loginPage.printInvalidCredentials();
            authMenu();
        }
        
    }

    private void invalidException(AppException e) {
        println(e.getMessage());
        authMenu();
    }
}
