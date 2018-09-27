package ru.dravn.paragontest.ui.presenrers;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import ru.dravn.paragontest.db.model.DataModel;
import ru.dravn.paragontest.ui.MainActivity;

/**
 * Created by Jeka on 25.09.2018.
 */
@InjectViewState
public class MasterPresenter extends MvpPresenter<MasterView> {
    private MasterModel mModel;
    private List<DataModel> mData;
    private String mFilter;

    public MasterPresenter() {
        this.mModel = new MasterModel();
    }


    public void getAdapter() {
        mFilter = MainActivity.ALL;
        mData = mModel.getAll();
        getViewState().setAdapter(mData);
    }

    public void updateAdapter(String filter) {
        mFilter = filter;

        switch (filter) {
            case MainActivity.ALL:
                mData = mModel.getAll();
                break;
            case MainActivity.FAVORITES:
                mData = mModel.getFavorite();
                break;
            case MainActivity.DISTRIBUTION:
                mData = mModel.getDistribution(3.0);
                break;
        }
        getViewState().updateAdapter(mData);
    }

    public void deleteData(int position) {
        mModel.delete(mData.get(position));
        updateAdapter(mFilter);
    }

    public void changeFavorite(DataModel date) {
        date.setFavorite(!date.getFavorite());
        mModel.update(date);
        updateAdapter(mFilter);
    }

    public Integer getId(int position) {
        return mData.get(position).getId();
    }
}
