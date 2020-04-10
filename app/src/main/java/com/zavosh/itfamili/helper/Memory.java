package com.zavosh.itfamili.helper;

import com.orhanobut.hawk.Hawk;

public class Memory {
    public static String phoneKey = "phone";
    public static String tokenCodKey = "tokenCodKey";
    public static String userCodKey = "userCode";
    public static String TOKEN = "TOKEN";
    public static String PasswordKey = "Password";

    public static void savePhone(String phone){
        Hawk.put(phoneKey,phone);
    }
    public static String loadPhone(){
        if (Hawk.contains(phoneKey))
            return Hawk.get(phoneKey);
        return "";
    }

    public static void savePassword(String phone){
        Hawk.put(PasswordKey,phone);
    }
    public static String loadPassword(){
        if (Hawk.contains(PasswordKey))
            return Hawk.get(PasswordKey);
        return "";
    }

    public static void saveTokenCode(String phone){
        Hawk.put(tokenCodKey,phone);
    }
    public static String loadTokenCode(){
        if (Hawk.contains(tokenCodKey))
            return Hawk.get(tokenCodKey);
        return "";
    }

    public static void saveUserCode(String phone){
        Hawk.put(userCodKey,phone);
    }
    public static String loadUserCode(){
        if (Hawk.contains(userCodKey))
            return Hawk.get(userCodKey);
        return "";
    }

    public static void saveToken(String phone){
        Hawk.put(TOKEN,phone);
    }
    public static String loadToken(){
        if (Hawk.contains(TOKEN))
            return Hawk.get(TOKEN);
        return "";
    }
}
