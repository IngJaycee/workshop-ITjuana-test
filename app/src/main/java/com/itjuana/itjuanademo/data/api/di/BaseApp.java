package com.itjuana.itjuanademo.data.api.di;

import android.app.Application;


/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */
public class BaseApp extends Application {
    NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        networkComponent = DaggerNetworkComponent
                .builder()
                .networkModule(new NetworkModule(this))
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
