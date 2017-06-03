package com.example.aum.smartyapp.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


// TODO: Auto-generated Javadoc

/**
 * The Class UtilFunctions.
 */
@SuppressLint("NewApi")
public class UtilFunctions {

    public static final String ALL_IMAGES_TAG = "HomeFragment";
    public static final String SETTINGS_TAG = "Settings";
    public static final String FULL_SCREEN_GALLERY = "DoodleDetail";
    public static final String IMAGE_FOLDERS_TAG = "Doodle";
    public static final String ABOUT_APP_TAG = "About";
    /**
     * The Constant IS_ISC.
     */
    // public static final boolean IS_JBMR2 = Build.VERSION.SDK_INT ==
    // Build.VERSION_CODES.JELLY_BEAN_MR2;
    public static final boolean IS_ISC = Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    /**
     * The Constant IS_GINGERBREAD_MR1.
     */
    public static final boolean IS_GINGERBREAD_MR1 = Build.VERSION.SDK_INT == Build.VERSION_CODES.GINGERBREAD_MR1;
    private static String CURRENT_TAG = null;

    public static void switchContent(int id, String TAG, FragmentActivity baseActivity, AnimationType transitionStyle) {

        Fragment fragmentToReplace = null;

        FragmentManager fragmentManager = baseActivity.getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
    }

    // If our current fragment is null, or the new fragment is different, we
    // need to change our current fragment


    public static String getVersion(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            return String.valueOf(pInfo.versionCode) + " " + pInfo.versionName;

        } catch (NameNotFoundException e) {
            return "1.0.1";
        }
    }

    public static void switchFragmentWithAnimation(int id, Fragment fragment, FragmentActivity activity, String TAG,
                                                   AnimationType transitionStyle) {

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (transitionStyle != null) {

        }

        CURRENT_TAG = TAG;

        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commit();
    }

    /**
     * Vibrate.
     *
     * @param context the context
     */
    public static void vibrate(Context context) {
        // Get instance of Vibrator from current Context and Vibrate for 400
        // milliseconds
        ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(100);
    }

    public static boolean isPortrait(Context context) {

        return true;
    }

    /**
     * The Enum AnimationType.
     */
    public enum AnimationType {

        /**
         * The slide left.
         */
        SLIDE_LEFT, /**
         * The slide right.
         */
        SLIDE_RIGHT, /**
         * The slide up.
         */
        SLIDE_UP, /**
         * The slide down.
         */
        SLIDE_DOWN, /**
         * The fade in.
         */
        FADE_IN, /**
         * The slide in slide out.
         */
        SLIDE_IN_SLIDE_OUT, /**
         * The fade out.
         */
        FADE_OUT
    }

}
