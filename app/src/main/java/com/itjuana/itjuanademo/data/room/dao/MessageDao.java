package com.itjuana.itjuanademo.data.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.itjuana.itjuanademo.data.room.entity.MessageEntity;
import com.itjuana.itjuanademo.data.room.entity.UserEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */
@Dao
public abstract class MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Single<Long> insertUser(MessageEntity messageEntity);

    @Query("SELECT * FROM MESSAGE_TABLE WHERE 1")
    public abstract Flowable<List<MessageEntity>> getAllList();


    @Transaction
    public void updateData(List<MessageEntity> listOfComments) {
        deleteAllComments();
        insertAll(listOfComments);
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<MessageEntity> listOfComments);

    @Query("DELETE FROM MESSAGE_TABLE")
    public abstract void deleteAllComments();

}