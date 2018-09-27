package ru.dravn.paragontest.ui.presenrers;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import ru.dravn.paragontest.db.model.DataModel;

/**
 * Created by Jeka on 25.09.2018.
 */

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {
    private MasterModel mModel;
    private DataModel mData;

    public DetailPresenter() {
        this.mModel = new MasterModel();
    }


    public void changeFavorite(DataModel data) {
        if (data.getId() == mData.getId()) {
            data.setFavorite(data.getFavorite());
            mModel.update(data);
            getViewState().setFavorite(data);
        }
    }

    public void setData(Integer id) {
        mData = mModel.getById(id);
        getViewState().setVersion(mData.getVersion());
        getViewState().setName(mData.getName());
        getViewState().setReleased(convertDate(mData.getReleased()));
        getViewState().setApi(mData.getApi());
        getViewState().setDistribution(mData.getDistribution());
        getViewState().setDescription(mData.getDescription());
        getViewState().setFavorite(mData);
    }

    private String convertDate(Date date) {
        return DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ENGLISH).format(date);
    }

}
