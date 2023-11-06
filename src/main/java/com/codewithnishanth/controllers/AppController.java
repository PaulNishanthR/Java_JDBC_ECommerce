package com.codewithnishanth.controllers;

import com.codewithnishanth.controllers.Implementation.IAppController;
import com.codewithnishanth.views.WelcomePage;

public class AppController implements IAppController {

    private final WelcomePage welcomePage;
    private final AuthController authController;

    public AppController() {
        this.welcomePage = new WelcomePage();
        this.authController = new AuthController();
    }

    @Override
    public void initiate() {
        welcomePage.welcomeMessage();
        authController.authMenu();
    }
}
