package com.itjuana.itjuanademo.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;

/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */
public abstract class PrivateId {
    @SuppressLint("HardwareIds")
    public static String getPrivateID (Context c){
        return Settings.Secure.getString(c.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
}
