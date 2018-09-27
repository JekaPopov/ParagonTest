package ru.dravn.paragontest.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ru.dravn.paragontest.db.model.DataModel;

/**
 * Created by Jeka on 24.09.2018.
 */

@Dao
public interface DataDao {
    @Insert
    void insert(DataModel dataModel);

    @Update
    void update(DataModel dataModel);

    @Delete
    void delete(DataModel dataModel);

    @Query("SELECT * FROM DataModel")
    List<DataModel> getAllData();


    @Query("SELECT * FROM DataModel WHERE id LIKE :id")
    DataModel getById(int id);

    @Query("SELECT * FROM DataModel WHERE distribution > :distribution")
    List<DataModel> getByDistribution(Double distribution);


    @Query("SELECT * FROM DataModel WHERE favorite LIKE :favorite")
    List<DataModel> getFavorite(Boolean favorite);
}