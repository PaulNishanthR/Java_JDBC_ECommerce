package com.codewithnishanth.utils;

import java.util.Scanner;

public class AppScanner {

    private static Scanner scanner;

    public static Scanner getScanner()
    {
        if(scanner==null)
        {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
