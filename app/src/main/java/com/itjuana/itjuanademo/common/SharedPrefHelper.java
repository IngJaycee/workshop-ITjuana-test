package com.itjuana.itjuanademo.common;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */
public class SharedPrefHelper {

    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSharedPref;
    private Context mContext;

    public SharedPrefHelper(Context mContext) {
        this.mSharedPref = mContext.getSharedPreferences("ITJuanPreferences",Context.MODE_PRIVATE);
        this.mEditor = mSharedPref.edit();
        this.mContext = mContext;
        mEditor.apply();
    }

    public String getUser(){
        return mSharedPref.getString("SHARED_USER","") ;//Constants.ENGLISH_TAG);
    }

    public void setUser(String user){
        mEditor.putString("SHARED_USER", user);
        mEditor.commit();
    }

}
