package ru.dravn.paragontest.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import ru.dravn.paragontest.R;
import ru.dravn.paragontest.db.model.DataModel;
import ru.dravn.paragontest.ui.eventBuses.FavoriteEventBus;
import ru.dravn.paragontest.ui.presenrers.DetailPresenter;
import ru.dravn.paragontest.ui.presenrers.DetailView;

/**
 * Created by Jeka on 24.09.2018.
 */

public class DetailFragment extends MvpAppCompatFragment implements DetailView {


    private View view;
    private TextView mVersionView, mNameView, mReleasedView, mApiView, mDistributionView, mDescriptionView;

    private ImageView mFavorite;
    private CardView mCardView;

    @InjectPresenter
    DetailPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_detail, container, false);
        init();

        return view;

    }

    void init() {
        mVersionView = view.findViewById(R.id.version);
        mNameView = view.findViewById(R.id.name);
        mReleasedView = view.findViewById(R.id.released);
        mApiView = view.findViewById(R.id.api);
        mDistributionView = view.findViewById(R.id.distribution);
        mDescriptionView = view.findViewById(R.id.description);
        mFavorite = view.findViewById(R.id.favorite);
        mCardView = view.findViewById(R.id.cardView);
    }


    public void setId(Integer id) {
        if (id == MainActivity.NO_ID) {
            mCardView.setVisibility(View.GONE);
        } else {
            presenter.setData(id);
            mCardView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void setVersion(String version) {
        mVersionView.setText(String.format(getString(R.string.version), version));
    }

    @Override
    public void setName(String name) {
        mNameView.setText(String.format(getString(R.string.name), name));
    }

    @Override
    public void setReleased(String released) {
        mReleasedView.setText(String.format(getString(R.string.released), released));
    }

    @Override
    public void setApi(Integer api) {
        mApiView.setText(String.format(getString(R.string.api), api));
    }

    @Override
    public void setDistribution(Double distribution) {
        mDistributionView.setText(String.format(getString(R.string.distribution), distribution));
    }

    @Override
    public void setDescription(String description) {
        mDescriptionView.setText(description);
    }

    @Override
    public void setFavorite(final DataModel data) {
        mFavorite
                .setImageResource(data.getFavorite() ? R.drawable.ic_favorite : R.drawable.ic_no_favorite);

        mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new FavoriteEventBus(data));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFavoriteEvent(FavoriteEventBus event) {
        presenter.changeFavorite(event.mFavorite);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        EventBus.getDefault().unregister(this);
        super.onDetach();
    }
}