package com.itjuana.itjuanademo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

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

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new MainRepository(application);
        repository.requestAllUsers();
        repository.requestAllMessages();
//        addUser(/*PrivateId.getPrivateID(application.getApplicationContext())*/1, "JC", "ITJuana Software Engineer");
    }

    public void sendMessage(String messageStr) {
        repository.insertMessage(new MessageEntity(messageStr, "JC"));
    }

    public Flowable<List<MessageEntity>> getAllMessages() {
        return repository.getAllMessages();
    }

    public Flowable<List<UserEntity>> getAllUsers() {
        return repository.getAllUsers();
    }

    public void addUser(int id, String name, String description) {
        repository.addUser(new UserEntity(id, name, description));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository.tearDown();
    }
}
