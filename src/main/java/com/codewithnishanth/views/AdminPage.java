package com.codewithnishanth.views;

import com.codewithnishanth.utils.StringUtil;

import static com.codewithnishanth.utils.AppOutput.println;

public class AdminPage {
    public void welcomeMsg() {
        println(StringUtil.ADMIN_WELCOME);
    }

    public void adminMenu() {
        println(StringUtil.ADMIN_CHOICE);
    }
}
