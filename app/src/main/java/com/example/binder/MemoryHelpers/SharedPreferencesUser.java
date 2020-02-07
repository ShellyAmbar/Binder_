package com.example.binder.MemoryHelpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.binder.R;

public class SharedPreferencesUser {

    public static void SaveKeyValueString(Activity activity, String key, String value){

        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String GetKeyValueString(Activity activity, String key){
        String value = "";
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);

        value = sharedPref.getString(key,"");
        return value;
    }
}
