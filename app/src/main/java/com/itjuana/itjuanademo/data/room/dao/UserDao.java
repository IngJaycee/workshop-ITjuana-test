package com.itjuana.itjuanademo.data.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.itjuana.itjuanademo.data.room.entity.UserEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */
@Dao
public abstract class UserDao {

    @Query("SELECT * FROM USER_TABLE WHERE 1")
    public abstract Flowable<List<UserEntity>> getAllList();

    @Transaction
    public void updateData(List<UserEntity> listOfUsers) {
        deleteAllUsers();
        insertAll(listOfUsers);
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<UserEntity> listOfUsers);

    @Query("DELETE FROM USER_TABLE")
    public abstract void deleteAllUsers();
}
