package ru.dravn.paragontest.ui.presenrers;

import java.util.List;

import ru.dravn.paragontest.App;
import ru.dravn.paragontest.db.AppDatabase;
import ru.dravn.paragontest.db.model.DataModel;

/**
 * Created by Jeka on 25.09.2018.
 */

class MasterModel {

    private AppDatabase mDatabase;

    MasterModel() {
        mDatabase = App.getInstance().getDatabase();
    }

    List<DataModel> getAll() {
        return mDatabase.getDataDao().getAllData();
    }


    List<DataModel> getFavorite() {
        return mDatabase.getDataDao().getFavorite(true);
    }

    List<DataModel> getDistribution(Double qty) {
        return mDatabase.getDataDao().getByDistribution(qty);
    }

    DataModel getById(int id) {
        return mDatabase.getDataDao().getById(id);
    }

    void delete(DataModel dataModel) {
        mDatabase.getDataDao().delete(dataModel);
    }

    void update(DataModel dataModel) {
        mDatabase.getDataDao().update(dataModel);
    }
}
