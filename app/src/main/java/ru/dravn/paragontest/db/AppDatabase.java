package ru.dravn.paragontest.db;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import ru.dravn.paragontest.db.converter.DateConverter;
import ru.dravn.paragontest.db.dao.DataDao;
import ru.dravn.paragontest.db.model.DataModel;

/**
 * Created by Jeka on 24.09.2018.
 */

@Database(entities = {DataModel.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DataDao getDataDao();

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}