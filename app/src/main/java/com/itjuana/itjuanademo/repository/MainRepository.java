package com.itjuana.itjuanademo.repository;

import android.app.Application;
import android.util.Log;

import com.itjuana.itjuanademo.data.api.ITJuanaInterface;
import com.itjuana.itjuanademo.data.api.di.BaseApp;
import com.itjuana.itjuanademo.data.room.dao.MessageDao;
import com.itjuana.itjuanademo.data.room.dao.UserDao;
import com.itjuana.itjuanademo.data.room.db.MainDatabase;
import com.itjuana.itjuanademo.data.room.entity.MessageEntity;
import com.itjuana.itjuanademo.data.room.entity.UserEntity;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */
public class MainRepository {
    private static final String TAG = "MainRepository";
    private MessageDao messageDAO;
    private UserDao userDao;
    private Disposable disposable = null;
    private Disposable disposable2 = null;

    @Inject
    ITJuanaInterface itJuanaInterface;

    public MainRepository(Application application) {
        MainDatabase mainDatabase = MainDatabase.getINSTANCE(application);
        messageDAO = mainDatabase.getMessagesDao();
        userDao = mainDatabase.getUserDao();
        ((BaseApp) application).getNetworkComponent().inject(this);
    }

    public Flowable<List<MessageEntity>> getAllMessages() {
        return messageDAO.getAllList();
    }

    public Flowable<List<UserEntity>> getAllUsers() {
        return userDao.getAllList();
    }

    public void insertMessage(MessageEntity messageEntity) {
        Disposable disposable = itJuanaInterface.addComment(messageEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(response -> {
                    Log.d(TAG, "insertMessage: " + response.toString());
                }, Throwable::printStackTrace);
    }

    public void requestAllUsers() {
        if (disposable == null) {
            disposable = Observable
                    .interval(3000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(l -> {
                        Disposable aDis = itJuanaInterface
                                .getAllUsers()
                                .subscribeOn(Schedulers.io())
                                .observeOn(Schedulers.io())
                                .subscribe(list -> {
                                    userDao.updateData(list);
                                    Log.d(TAG, "requestAllUsers in LOOP" + list.toString());
                                }, Throwable::printStackTrace);
                    }, Throwable::printStackTrace);
        }
    }

    public void requestAllMessages() {
        if (disposable2 == null) {
            disposable2 = Observable
                    .interval(3000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(l -> {
                        Disposable aDis = itJuanaInterface
                                .getAllComments()
                                .subscribeOn(Schedulers.io())
                                .observeOn(Schedulers.io())
                                .subscribe(list -> {
                                    messageDAO.updateData(list);
                                    Log.d(TAG, "requestAllUsers in LOOP" + list.toString());
                                }, Throwable::printStackTrace);
                    }, Throwable::printStackTrace);
        }
    }

    public void addUser(UserEntity user) {
        Disposable disposable = itJuanaInterface.addUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(s -> {
                    Log.d(TAG, "addUser: " + s.toString());

                }, Throwable::printStackTrace);
    }


    public void tearDown() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
        if (disposable2 != null && !disposable2.isDisposed()) {
            disposable2.dispose();
            disposable2 = null;
        }
    }
}