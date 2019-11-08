package com.itjuana.itjuanademo.data.api.di;

import com.itjuana.itjuanademo.repository.MainRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */
@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {
    void inject(MainRepository mainRepository);
}
