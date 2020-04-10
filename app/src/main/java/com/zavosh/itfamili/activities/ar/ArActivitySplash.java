package com.zavosh.itfamili.activities.ar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.ArCoreApk;
import com.zavosh.itfamili.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ArActivitySplash extends AppCompatActivity {

    TextView textMessage;
    TextView textCode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ar_activity_splash);


        textMessage = (TextView) findViewById(R.id.textMessage);
        textCode = (TextView) findViewById(R.id.textCode);

    }






    @Override
    protected void onResume() {

        super.onResume();



        if(ArSystem.activityStackNumber >1)
        {
            ArSystem.activityStackNumber = 0;
            //Intent arIntentBackToApp = new Intent(ArActivitySplash.this, ArLauncherActivity.class);
            //startActivity(arIntentBackToApp);

            ArActivitySplash.this.finish();
            return;
        }

        ArSystem.activityStackNumber = 1;



        textMessage.setText(getApplicationContext().getResources().getString(R.string.ar_init_text));
        textCode.setText("");

        arCheckAvailibility();


        if(letARContinue && ArSystem.gotARCodeApkTooOld==false) {

            // Should be rechecked: either supported, or unknown

            ArCoreApk.Availability availabilityCheck3 =
                    ArCoreApk.getInstance().checkAvailability(ArActivitySplash.this);

            if(ArSystem.isEmulator()){
                Log.d("availability", "Emulator Mode.");
                availabilityCheck3 = ArCoreApk.Availability.SUPPORTED_INSTALLED;}

            if(availabilityCheck3 == ArCoreApk.Availability.SUPPORTED_APK_TOO_OLD) {
                ArSystem.gotARCodeApkTooOld = true;
                ArSystem.saveToDevicePref_GotApkTooOld(getApplicationContext(), true);
            }


            Log.d("availability", "AvailabilityCheck3: " + availabilityCheck3);

            if(ArSystem.gotARCodeApkTooOld == false) {
                if (availabilityCheck3 == ArCoreApk.Availability.SUPPORTED_INSTALLED) {

                    preprocessAR();

                    ArSystem.activityStackNumber = 2;
                    Intent arIntent = new Intent(ArActivitySplash.this, ArActivity.class);
                    arIntent.putExtra("AvailabilityCode", availabilityCheck3.toString());
                    startActivity(arIntent);
                } else {
                    requestGooglePlayServicesForARInstllation();
                }
            }
            else
            {
                notSupportedAtAll();
            }

        }
        else {

            notSupportedAtAll();

        }

        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

            }
        }, 500);*/

    }

    private void notSupportedAtAll() {
        textMessage.setText(getApplicationContext().getResources().getString(R.string.ar_notsupported_text));
        textCode.setText(""); //availabilityMessage);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArActivitySplash.this.finish();
            }
        }, 2000);*/
    }

    // Set to true ensures requestInstall() triggers installation if necessary.
    // private boolean mUserRequestedPlayStoreInstall = true;

    private void requestGooglePlayServicesForARInstllation() {
        //https://developers.google.com/ar/develop/java/enable-arcore#request_camera_permission_ar_optional_and_ar_required_apps

        textMessage.setText(getApplicationContext().getResources().getString(R.string.ar_installgpsar_text));
        textCode.setText("");


        installGooglePlayServicesForARLocally();

        /*
        // Make sure Google Play Services for AR is installed and up to date.
        try {
            switch (ArCoreApk.getInstance().requestInstall(this, mUserRequestedPlayStoreInstall)) {
                case INSTALLED:
                    // Success, create the AR session.

                    break;
                case INSTALL_REQUESTED:
                    // Ensures next invocation of requestInstall() will either return
                    // INSTALLED or throw an exception.
                    mUserRequestedPlayStoreInstall = false;
                    return;
            }
        } catch (UnavailableUserDeclinedInstallationException e) {
            installGooglePlayServicesForARLocally();


            return;

        } catch (Exception e) {  // Current catch statements.
            return;  // mSession is still null.
        }
         */
    }

    private void installGooglePlayServicesForARLocally() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            // Assets can be read, so there is no need to copy them to internal storage
            File folder = getFilesDir();
            File f = new File(folder, "ar_gpsar");
            if(f.exists()==false)
                f.mkdirs();

            copyFileOrDirFromAssetsToInternalStorage("ar_gpsar", false);


            Toast.makeText(ArActivitySplash.this,
                    getApplicationContext().getResources().getString(R.string.ar_installgpsar_text), Toast.LENGTH_LONG)
                    .show();

            // Let the 'installing google play services..' message be seen and then install the apk.
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Run local 'Google Play Services for AR' APK

                    installGooglePlayServicesMainRepoForAR();
                }
            }, 1500);


        }
        else {
            textMessage.setText(getApplicationContext().getResources().getString(R.string.ar_requiresandroid7plus_text));
            textCode.setText("");
        }
    }




    private void installGooglePlayServicesMainRepoForAR()
    {
        String apkName = ArSystem.googlePlayServicesForArApkName;
        String apkPath = getFilesDir() + "/" + "ar_gpsar/"+ apkName;

        ArSystem.installAPK(ArActivitySplash.this, new File(apkPath));
    }

    /*
    Deprecated

    private void installGooglePlayServicesForAR(String arch) {

        String apkName="", apkPath;
        switch (arch)
        {
            case archlabel_x86:
                apkName = "gpsar_1.15.200121049_arch_(x86_64,x86).apk";
                break;
            case archlabel_arm32:
                apkName = "gpsar_1.15.200121043_arch_(armeabi-v7a).apk";
                break;
            case archlabel_arm64_arm32:
                apkName = "gpsar_1.15.200121046_arch_(arm64-v8a,armeabi-v7a).apk";
                break;
        }

        apkPath = getFilesDir() + "/" + "ar_gpsar/"+ apkName;//getDataDir().getPath() + "/" + "ar/gpsar/"+ apkName;

        Log.d("availability", "Architecture: " + apkPath);

        ArSystem.installAPK(this, new File(apkPath));
    }
     */

    //Use control variables here later..
    private boolean letARContinue = true;

    private String availabilityMessage = "";



    public void arCheckAvailibility() {

           /*
            Note, checkAvailability() may need to query network resources to determine whether the device supports ARCore.
            During this time, it will return UNKNOWN_CHECKING. To reduce the perceived latency and pop-in, apps should call
            checkAvailability() once early in it's life cycle to initiate the query, ignoring the returned value. This way
            a cached result will be available immediately when maybeEnableArButton() is called.
            More Info:
            https://developers.google.com/ar/develop/java/enable-arcore#check_supported
            https://developers.google.com/ar/reference/java/arcore/reference/com/google/ar/core/ArCoreApk.Availability
             */


        ArCoreApk.Availability availabilityCheck2 = ArCoreApk.getInstance().checkAvailability(ArActivitySplash.this);

        if(ArSystem.isEmulator()){
            Log.d("availability", "Emulator Mode.");
            availabilityCheck2 = ArCoreApk.Availability.SUPPORTED_INSTALLED;}


        if(availabilityCheck2 == ArCoreApk.Availability.SUPPORTED_APK_TOO_OLD)
        {
            ArSystem.gotARCodeApkTooOld = true;
            ArSystem.saveToDevicePref_GotApkTooOld(getApplicationContext(), true);
        }



        availabilityMessage = availabilityCheck2.toString();

        Log.d("availability", "AvailabilityCheck2: "+ availabilityCheck2.toString());

        if (availabilityCheck2.isTransient()) {
            Log.d("availability", "Inside Splash. isTransient.");
            // Re-query at 5Hz while compatibility is checked in the background.
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    arCheckAvailibility();
                }
            }, 200);
        }

        if(availabilityCheck2 == ArCoreApk.Availability.UNSUPPORTED_DEVICE_NOT_CAPABLE
                || availabilityCheck2 == ArCoreApk.Availability.SUPPORTED_APK_TOO_OLD)
        {
            Log.d("availability", "Inside Splash. UNSUPPORTED_DEVICE_NOT_CAPABLE.");

            letARContinue = false;
        }
    }



    private void copyFileOrDirFromAssetsToInternalStorage(String path, boolean overwrite) {
        AssetManager assetManager = this.getAssets();
        String[] assets = null;
        try {
            assets = assetManager.list(path);
            if (assets.length == 0) {
                copyFileFromAssetsToInternalStorage(path, overwrite);
            } else {
                String fullPath = getFilesDir() + "/" + path ;//getDataDir().getPath() + "/" + path ;
                File dir = new File(fullPath);
                if (!dir.exists())
                    dir.mkdir();
                for (int i = 0; i < assets.length; ++i) {
                    copyFileOrDirFromAssetsToInternalStorage(path + "/" + assets[i], overwrite);
                }
            }
        } catch (IOException ex) {
            Log.e("arfiles", "I/O Exception", ex);
        }
    }

    private void copyFileFromAssetsToInternalStorage(String filename, boolean overwrite) {

        String newFileName = getFilesDir() +  "/" + filename; //getDataDir().getPath() +  "/" + filename;

        try {

            File f = new File(newFileName);
            if (overwrite == false) {

                if (f.exists()) {
                    Log.d("arfiles", "Duplicate Found: " + newFileName);
                    return;
                }
            } else {
                if (f.exists())
                    f.delete();
            }

        }catch (Exception e){
            Log.e("arfiles", e.getMessage());
        }


        AssetManager assetManager = this.getAssets();

        InputStream in = null;
        OutputStream out = null;
        try {

            in = assetManager.open(filename);
            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("arfiles", e.getMessage());
        }

    }

    private static void copyFile(File src, File dst, boolean overwrite) throws IOException {

        try {
            File f = dst; //new File(dst.getPath() + src.getName());
            if (overwrite == false) {

                if (f.exists()) {
                    Log.d("arfiles", "Duplicate Found: " + f.getName());
                    return;
                }
            }else{
                if (f.exists())
                    f.delete();
            }
        }catch (Exception e){
            Log.e("arfiles", e.getMessage());
        }


        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    private void copyDirFilesToDir(File sourceDir, File targetDir, boolean overwrite)
    {
        Log.d("arfiles", "SourceFolder: " + sourceDir.getPath());
        Log.d("arfiles", "TargetFolder: " + targetDir.getPath());

        File[] files = sourceDir.listFiles();

        if(files!=null && files.length>0)
        {
            Log.d("arfiles", "File Count: " + files.length);

            for (int i = 0; i < files.length; i++) {
                Log.d("Files", "FileName:" + files[i].getName());

                try{
                    copyFile(files[i],
                            new File(targetDir.getPath() + "/" +files[i].getName()), overwrite);

                    //new File(dst.getPath() + src.getName());
                } catch (IOException ex) {
                    Log.e("arfiles", "I/O Exception", ex);
                }

            }
        }
    }



    private void preprocessAR()
    {
        // process AR folders and database

        // Copying the contents of the 'assets' folder into the 'ar' directory in the internal storage




        /*if(ArSystem.assetsStaged == false) {

            Log.d("assetsstage", "assetsstage step1");

            ArSystem.assetsStaged = ArSystem.readFromDevicePref_AssetsStaged(getApplicationContext());

            Log.d("assetsstage", "assetsstage step2 " + ArSystem.assetsStaged);

            if(ArSystem.assetsStaged == false) {
                Log.d("assetsstage", "assetsstage step3");
                copyFileOrDirFromAssetsToInternalStorage("ar", true);
                copyFileOrDirFromAssetsToInternalStorage("ar_other", true);
                ArSystem.saveToDevicePref_AssetsStaged(getApplicationContext(), true);
                ArSystem.assetsStaged = true;
                Log.d("assetsstage", "assetsstage step3.1");
            }
        }*/

        Log.d("assetsstage", "assetsstage step1");

        if(ArSystem.assetsStaged == false) {
            Log.d("assetsstage", "assetsstage step2");

            //If required files don't exist, copy from assets to the related folder. Overwrite: false
            //These files are not downloaded from server, files like: ar_typedb.imgdb
            copyFileOrDirFromAssetsToInternalStorage("ar", false);

            //Copy and overwrite files from ar_other of the assets to the 'ar_other' of the internal storage
            copyFileOrDirFromAssetsToInternalStorage("ar_other", true);

            ArSystem.assetsStaged = true;
            Log.d("assetsstage", "assetsstage step3");
        }


        if(ArSystem.isInDevelopmentDebug) {
            copyDirFilesToDir(new File(getFilesDir() + "/" + "ar_other/debug/ar_mock"),
                    new File(getFilesDir() + "/" + "ar"), true);
        }




        // Read files from the ar folder and fill in the ar descriptor class


        //ArFileDescriptor arDescriptor = new ArFileDescriptor(getApplicationContext());

        //arDescriptor.siftProcessArFiles();

        //ArSystem.arFileDescriptor = arDescriptor;

        /*for(ArFileRefImage r : arDescriptor.refImages)
        {
            Log.d("descriptor", "refImage, file-name: " + r.file.getName() + " refnum: " +
                    r.refnum + " uv:" + r.uv + " iscover:" + r.isCover + " file-type:" + r.fileType +
                    " mag:" + r.mag);
        }
        */



        // Process the scenario json file and fill in the related ar classes


        //Moshi moshi = new Moshi.Builder().build();
        /*Type type = Types.newParameterizedType(List.class, Person.class);
        JsonAdapter<List> adapter = moshi.adapter(type);
        List<Person> persons = adapter.fromJson(jsonResponse);*/









    }

}
