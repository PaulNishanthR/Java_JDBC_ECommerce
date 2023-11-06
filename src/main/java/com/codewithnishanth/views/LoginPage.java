package com.codewithnishanth.views;

import com.codewithnishanth.utils.StringUtil;

import static com.codewithnishanth.utils.AppOutput.println;

public class LoginPage {
    public void printInvalidCredentials() {
        println(StringUtil.INVALID_CREDENTIALS);
    }

    public void loginSuccess() {
        println(StringUtil.LOGIN_SUCCESS);
    }
}
