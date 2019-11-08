package com.itjuana.itjuanademo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.itjuana.itjuanademo.common.PrivateId;
import com.itjuana.itjuanademo.data.room.entity.MessageEntity;
import com.itjuana.itjuanademo.data.room.entity.UserEntity;
import com.itjuana.itjuanademo.repository.MainRepository;

import java.util.List;

import io.reactivex.Flowable;


/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */
public class MainViewModel extends AndroidViewModel {

    private MainRepository repository;
    private volatile String DEVICE_ID;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new MainRepository(application);
        repository.requestAllUsers();
        repository.requestAllMessages();
        DEVICE_ID = PrivateId.getPrivateID(application);
        addUser("Undefined", "WorkShoper Atendee.");
    }

    public void sendMessage(String messageStr) {
        repository.insertMessage(new MessageEntity(0, messageStr, "JC"));
    }

    public Flowable<List<MessageEntity>> getAllMessages() {
        return repository.getAllMessages();
    }

    public Flowable<List<UserEntity>> getAllUsers() {
        return repository.getAllUsers();
    }

    public void addUser(String name, String description) {
        repository.addUser(new UserEntity(DEVICE_ID, name, description));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository.tearDown();
    }
}
