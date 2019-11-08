package com.itjuana.itjuanademo.data.room.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.itjuana.itjuanademo.data.room.dao.MessageDao;
import com.itjuana.itjuanademo.data.room.dao.UserDao;
import com.itjuana.itjuanademo.data.room.entity.MessageEntity;
import com.itjuana.itjuanademo.data.room.entity.UserEntity;


/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */

@Database(entities = {MessageEntity.class, UserEntity.class}, version = 2, exportSchema = false)
public abstract class MainDatabase extends RoomDatabase {
    private static volatile MainDatabase INSTANCE;

    public abstract MessageDao getMessagesDao();
    public abstract UserDao getUserDao();


    public static MainDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (MainDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(), MainDatabase.class, "Main_Database.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}