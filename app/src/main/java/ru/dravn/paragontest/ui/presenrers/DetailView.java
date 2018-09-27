package ru.dravn.paragontest.ui.presenrers;

import com.arellomobile.mvp.MvpView;

import ru.dravn.paragontest.db.model.DataModel;

/**
 * Created by Jeka on 25.09.2018.
 */

public interface DetailView extends MvpView {

    void setFavorite(DataModel data);

    void setVersion(String version);

    void setName(String name);

    void setReleased(String released);

    void setApi(Integer api);

    void setDistribution(Double distribution);

    void setDescription(String description);
}
