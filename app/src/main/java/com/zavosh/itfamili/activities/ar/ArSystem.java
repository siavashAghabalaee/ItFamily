package com.zavosh.itfamili.activities.ar;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.ar.core.ArCoreApk;
import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.zavosh.itfamili.BuildConfig;
import com.zavosh.itfamili.R;
import com.zavosh.itfamili.activities.ar.arfile.ArFileDescriptor;

import java.io.File;


public class ArSystem {

    public static  final boolean isInDevelopmentDebug = false;
    public static final String googlePlayServicesForArApkName = "Google_Play_Services_for_AR_1.15.apk";

    public static ArFileDescriptor arFileDescriptor;

    public static int activityStackNumber=0;
    public static boolean gotARCodeApkTooOld = false;
    public static boolean assetsStaged = false;

    private static final String TAG = "ARLogger";
    private static final double MIN_OPENGL_VERSION = 3.0;

    private static final String archlabel_arm64_arm32= "(arm64-v8a,armeabi-v7a)";
    private static final String archlabel_x86 = "(x86_64,x86)";
    private static final String archlabel_arm32 = "(armeabi-v7a)";

    private ArSystem() {

    }



    /**
     * Creates and shows a Toast containing an error message. If there was an exception passed in it
     * will be appended to the toast. The error will also be written to the Log
     */
    public static void displayError(
            final Context context, final String errorMsg, @Nullable final Throwable problem) {
        final String tag = context.getClass().getSimpleName();
        final String toastText;
        if (problem != null && problem.getMessage() != null) {
            Log.e(tag, errorMsg, problem);
            toastText = errorMsg + ": " + problem.getMessage();
        } else if (problem != null) {
            Log.e(tag, errorMsg, problem);
            toastText = errorMsg;
        } else {
            Log.e(tag, errorMsg);
            toastText = errorMsg;
        }

        new Handler(Looper.getMainLooper())
                .post(
                        () -> {
                            Toast toast = Toast.makeText(context, toastText, Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        });
    }

    private static Session createArSession(
            Activity activity, boolean installRequested, Config.LightEstimationMode lightEstimationMode)
            throws UnavailableException {
        Session session = null;
        // if we have the camera permission, create the session
        if (hasCameraPermission(activity)) {
            Log.d("check-res", "if we have the camera permission, create the session");

            switch (ArCoreApk.getInstance().requestInstall(activity, !installRequested)) {
                case INSTALL_REQUESTED:
                    installRequested = true;
                    return null;
                case INSTALLED:
                    break;
            }

            Log.d("check-res", "now checking the session(1)");
            session = new Session(activity);
            Log.d("check-res", "now checking the session(2)");
            // IMPORTANT!!!  ArSceneView requires the `LATEST_CAMERA_IMAGE` non-blocking update mode.
            Config config = new Config(session);
            config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
            config.setLightEstimationMode(lightEstimationMode);
            session.configure(config);
        }
        return session;
    }

    /**
     * Creates an ARCore session, requesting ARCore installation if necessary. This checks for the
     * CAMERA permission, and if granted, checks the state of the ARCore installation and requests
     * installation if not already installed. If there is a problem an exception is thrown. Care must
     * be taken to update the installRequested flag as needed to avoid an infinite checking loop. It
     * should be set to true if null is returned from this method, and called again when the
     * application is resumed.
     *
     * @param activity            - the activity currently active.
     * @param lightEstimationMode - the light estimation mode to be set on the returned session.
     */
    public static Session createArSessionWithInstallRequest(
            Activity activity, Config.LightEstimationMode lightEstimationMode)
            throws UnavailableException {
        return createArSession(activity, true, lightEstimationMode);
    }

    /**
     * Creates an ARCore session, but does not install ARCore even if it is unavailable. This checks
     * for the CAMERA permission, and if granted, checks the state of the ARCore installation. If
     * there is a problem an exception is thrown. Care must be taken to update the installRequested
     * flag as needed to avoid an infinite checking loop. It should be set to true if null is returned
     * from this method, and called again when the application is resumed.
     *
     * @param activity            - the activity currently active.
     * @param lightEstimationMode - the light estimation mode to be set on the returned session.
     */
    public static Session createArSessionNoInstallRequest(
            Activity activity, Config.LightEstimationMode lightEstimationMode)
            throws UnavailableException {
        return createArSession(activity, false, lightEstimationMode);
    }

    /**
     * Check to see we have the necessary permissions for this app, and ask for them if we don't.
     */
    public static void requestCameraPermission(Activity activity, int requestCode) {
        ActivityCompat.requestPermissions(
                activity, new String[]{Manifest.permission.CAMERA}, requestCode);
    }

    /**
     * Check to see we have the necessary permissions for this app.
     */
    public static boolean hasCameraPermission(Activity activity) {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Check to see if we need to show the rationale for this permission.
     */
    public static boolean shouldShowRequestPermissionRationale(Activity activity) {
        return ActivityCompat.shouldShowRequestPermissionRationale(
                activity, Manifest.permission.CAMERA);
    }

    /**
     * Launch Application Setting to grant permission.
     */
    public static void launchPermissionSettings(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        activity.startActivity(intent);
    }

    public static void handleSessionException(
            Activity activity, UnavailableException sessionException) {

        String message;
        if (sessionException instanceof UnavailableArcoreNotInstalledException) {
            message = "Please install ARCore";
        } else if (sessionException instanceof UnavailableApkTooOldException) {
            message = "Please update ARCore";
        } else if (sessionException instanceof UnavailableSdkTooOldException) {
            message = "Please update this app";
        } else if (sessionException instanceof UnavailableDeviceNotCompatibleException) {
            message = "This device does not support AR";
        } else {
            message = "Failed to create AR session";
            Log.e(TAG, "Exception: " + sessionException);
        }
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Returns false and displays an error message if Sceneform can not run, true if Sceneform can run
     * on this device.
     *
     * <p>Sceneform requires Android N on the device as well as OpenGL 3.0 capabilities.
     *
     * <p>Finishes the activity if Sceneform can not run
     */
    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e(TAG, activity.getApplicationContext().getResources().getString(R.string.ar_requiresandroid7plus_text));
            Toast.makeText(activity, activity.getApplicationContext().getResources().getString(R.string.ar_requiresandroid7plus_text), Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "AR requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "AR requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }

    public static void installAPK(Context ctx, File updateFile) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            Uri apkUri = FileProvider.getUriForFile(
                    ctx, BuildConfig.APPLICATION_ID
                            + ".provider", updateFile);
            Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
            intent.setData(apkUri);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            ctx.startActivity(intent);
        }else{
            Uri    apkUri = Uri.fromFile(updateFile);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(apkUri,
                    "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(intent);
        }
    }

    /**
     Requires API-Level 21+
     */
    private String getCpuArchitectureLabel() {
        String arch = archlabel_arm64_arm32; //default
        String[] SUPPORTED_32_BIT_ABIS = Build.SUPPORTED_32_BIT_ABIS;
        String[] SUPPORTED_64_BIT_ABIS = Build.SUPPORTED_64_BIT_ABIS;

        if(SUPPORTED_64_BIT_ABIS.length>0) {
            for(String term : SUPPORTED_64_BIT_ABIS)
            {
                if(term.toLowerCase().contains("x86"))
                    arch = archlabel_x86;
            }
        }
        else {
            for(String term : SUPPORTED_32_BIT_ABIS)
            {
                if(term.toLowerCase().contains("x86"))
                    arch = archlabel_x86;
                else
                    arch = archlabel_arm32;
            }
        }
        return arch;
    }

    public static boolean isEmulator() {
        return Build.MODEL.contains("Android SDK built for x86");
    }

    public static void saveToDevicePref_AssetsStaged(Context ctx, Boolean assetsStaged) {
        String _key = ctx.getResources().getString(R.string.ar_pref_assetsstaged);
        SharedPreferences sharedPref = ctx.getSharedPreferences(
                _key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(_key, assetsStaged);
        editor.commit();
    }

    public static Boolean readFromDevicePref_AssetsStaged(Context ctx)
    {
        String _key = ctx.getResources().getString(R.string.ar_pref_assetsstaged);
        SharedPreferences sharedPref = ctx.getSharedPreferences(_key, Context.MODE_PRIVATE);
        boolean defaultValue = false;
        return sharedPref.getBoolean(_key, defaultValue);
    }

    public static void saveToDevicePref_GotApkTooOld(Context ctx, Boolean gotApkTooOld) {
        String _key = ctx.getResources().getString(R.string.ar_pref_gotarcodeapktooold);
        SharedPreferences sharedPref = ctx.getSharedPreferences(
                _key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(_key, gotApkTooOld);
        editor.commit();
    }

    public static Boolean readFromDevicePref_GotApkTooOld(Context ctx)
    {
        String _key = ctx.getResources().getString(R.string.ar_pref_gotarcodeapktooold);
        SharedPreferences sharedPref = ctx.getSharedPreferences(_key, Context.MODE_PRIVATE);
        boolean defaultValue = false;
        return sharedPref.getBoolean(_key, defaultValue);
    }


    public static int getRandomIntegerBetweenRange(int min, int max){
        int x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }

}

