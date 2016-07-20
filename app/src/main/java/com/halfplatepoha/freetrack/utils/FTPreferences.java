package com.halfplatepoha.freetrack.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by surajsau on 16/7/16.
 */
public class FTPreferences {
    private static final String TF_PREF_FILE_NAME = "tf_pref";
    private static SharedPreferences sharedpreferences;

    public static void init(Context appCtx) {

        /**
         * Don't initialize again if it's already initialized
         */
        if (null == sharedpreferences) {

            sharedpreferences = appCtx.getSharedPreferences(TF_PREF_FILE_NAME, Context.MODE_PRIVATE);
        }

    }

    public static void setInPref(String key, String value) {

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void setInPref(String key, boolean value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void setInPref(String key, long value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void setInPref(String key, float value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static void setInPref(String key, int value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static String getFromPref(String key, String defaultValue) {
        return sharedpreferences.getString(key, defaultValue);
    }

    public static boolean getBooleanFromPref(String key, Boolean defaultValue) {
        return sharedpreferences.getBoolean(key, defaultValue);
    }

    public static String getStringFromPref(String key, String defaultValue) {
        return sharedpreferences.getString(key, defaultValue);
    }

    public static long getLongFromPref(String key, long defaultValue) {
        return sharedpreferences.getLong(key, defaultValue);
    }

    public static int getIntFromPref(String key, int defaultValue) {
        return sharedpreferences.getInt(key, defaultValue);
    }

    public static void removeFromPref(String key) {
        sharedpreferences.edit().remove(key).apply();
    }

    public static float getFloatFromPref(String appVersion, float defaultValue) {
        float ret = sharedpreferences.getFloat(appVersion, defaultValue);
        return ret;
    }

    public static HashSet<String> getSetFromPreferences(String emailSetKey) {


        HashSet<String> emailhashSet = (HashSet<String>) sharedpreferences.getStringSet(emailSetKey, new HashSet<String>());
        return emailhashSet;
    }

    public static ArrayList<String> getListFromPreferences(String listKey) {


        String strData = sharedpreferences.getString(listKey, "");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(TextUtils.split(strData, ",")));
        return list;
    }



    public static void clearUserData(Context context) {
        Log.d("","clearing user data");
        sharedpreferences.edit().clear().commit();
    }


}
