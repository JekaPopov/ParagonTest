package ru.dravn.paragontest;

import android.app.Application;

import com.huma.room_for_asset.RoomAsset;

import ru.dravn.paragontest.db.AppDatabase;

/**
 * Created by Jeka on 24.09.2018.
 */

public class App extends Application {

    public static App sInstance;
    private AppDatabase mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mDatabase = RoomAsset.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return sInstance;
    }

    public AppDatabase getDatabase() {
        return mDatabase;
    }
}