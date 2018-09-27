package ru.dravn.paragontest.ui.presenrers;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.dravn.paragontest.db.model.DataModel;

/**
 * Created by Jeka on 25.09.2018.
 */
public interface MasterView extends MvpView {

    void setAdapter(List<DataModel> data);

    void updateAdapter(List<DataModel> data);
}
