package com.zavosh.itfamily.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.inputmethod.InputMethodManager;

import java.text.NumberFormat;
import java.util.Locale;

public class PublicMethods {
    public static String _device;


    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getAndroidApi() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    public static String getAppVersion(Context context) {
        String version = "0.0.0";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static String convert(String price) {
        String newPrice = price.trim();
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String numberAsString = numberFormat.format(Integer.parseInt(newPrice));
        return numberAsString;
    }

    public static String getDeviceString() {
        if (_device != null)
            return _device;
        if (Build.MODEL.contains(Build.MANUFACTURER)) {
            _device = Build.MODEL;
            return _device;
        }
        _device = Build.MANUFACTURER + " " + Build.MODEL;
        return _device;
    }


    public static String getDate(String date) {
        String finalDate = "";
        String[] split = date.split("/");
        String day = split[0];
        String month = split[1];
        String year = split[2];
        finalDate = year + "/" + month + "/" + day;

        return finalDate;
    }

    public static void share(String title,String link , Context context){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, title+"  "+link);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        context.startActivity(shareIntent);
    }

    public static void openLink(String link, Context context){
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(link));
            context.startActivity(i);
        }catch (Exception e){}
    }

}
