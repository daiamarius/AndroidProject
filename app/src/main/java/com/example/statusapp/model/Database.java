package com.example.statusapp.model;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.statusapp.model.service.Service;
import com.example.statusapp.model.service.ServiceTagsCrossRef;
import com.example.statusapp.model.service.UserTag;


@androidx.room.Database(entities = {Service.class, UserTag.class, ServiceTagsCrossRef.class},version = 1)
public abstract class Database extends RoomDatabase {
    private final String TAG = "Database";
    private static final String BASE_URL = "http://192.168.0.105:5000/api/";

    private static Database instance;
    public abstract ServiceDao dao();

    public static synchronized Database getInstance(Context ctx){
        if(instance==null){
            instance = Room.databaseBuilder(ctx.getApplicationContext(),Database.class,"statusapp_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
