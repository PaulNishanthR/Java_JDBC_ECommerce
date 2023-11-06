package com.codewithnishanth.utils;

import static com.codewithnishanth.utils.AppScanner.getScanner;

public class AppInput {
    public static String enterString(String msg)
    {
        AppOutput.print(msg);
        return getScanner().nextLine();
    }
    public static int enterInteger(String msg) throws AppException {
        AppOutput.print(msg);
        int input;
        try{
            input = Integer.parseInt(getScanner().nextLine());
        }
        catch (Exception e)
        {
            throw new AppException(StringUtil.INVALID_CHOICE);
        }
        return input;
    }
    public static double enterDouble(String msg) throws AppException {
        AppOutput.print(msg);
        double input;
        try{
            input = Double.parseDouble(getScanner().nextLine());
        }
        catch (Exception e)
        {
            throw new AppException(StringUtil.INVALID_CHOICE);
        }
        return input;
    }
}
