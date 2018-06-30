package com.brownjee.journals;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Brownjee on 6/28/2018.
 */

public class SharedPref {
    SharedPreferences sharepreferences;

    public static SharedPref instance = null;

    public static SharedPref getInstance()
    {

        if (instance == null) {
            synchronized (SharedPref.class) {
                instance = new SharedPref();
            }
        }
        return instance;
    }
    public void saveISLogged_IN(Context context, Boolean isLoggedin) {
        sharepreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharepreferences.edit();
        editor.putBoolean("IS_LOGIN", isLoggedin);
        editor.commit();
    }

    public boolean getISLogged_IN(Context context) {
        sharepreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharepreferences.getBoolean("IS_LOGIN", false);
    }
}
