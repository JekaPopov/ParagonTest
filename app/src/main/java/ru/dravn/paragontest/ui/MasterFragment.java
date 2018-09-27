package ru.dravn.paragontest.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import ru.dravn.paragontest.R;
import ru.dravn.paragontest.db.model.DataModel;
import ru.dravn.paragontest.ui.eventBuses.FavoriteEventBus;
import ru.dravn.paragontest.ui.eventBuses.FilterEventBus;
import ru.dravn.paragontest.ui.presenrers.MasterPresenter;
import ru.dravn.paragontest.ui.presenrers.MasterView;

/**
 * Created by Jeka on 24.09.2018.
 */

public class MasterFragment extends MvpAppCompatFragment implements MasterView, View.OnClickListener, View.OnLongClickListener {

    private OnItemSelectedFromTheList mListener;
    private RecyclerView mRecyclerView;
    private DataAdapter mAdapter;


    @InjectPresenter
    MasterPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master, container, false);

        mRecyclerView = view.findViewById(R.id.list);
        mPresenter.getAdapter();


        return view;
    }

    @Override
    public void setAdapter(List<DataModel> data) {
        mAdapter = new DataAdapter(getContext(), data);
        mAdapter.setOnClickListener(this);
        mAdapter.setOnLongClickListener(this);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void updateAdapter(List<DataModel> data) {
        mAdapter.setData(data);
        mAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFilterEvent(FilterEventBus event) {
        mPresenter.updateAdapter(event.mFilter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFavoriteEvent(FavoriteEventBus event) {
        mPresenter.changeFavorite(event.mFavorite);
    }


    @Override
    public void onClick(View view) {

        mRecyclerView.getAdapter().notifyItemChanged(mAdapter.mSelectedPos);
        mAdapter.mSelectedPos = mRecyclerView.getChildLayoutPosition(view);
        mRecyclerView.getAdapter().notifyItemChanged(mAdapter.mSelectedPos);

        mListener.onItemSelect(mPresenter.getId(mRecyclerView.getChildLayoutPosition(view)));
    }

    @Override
    public boolean onLongClick(final View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Вы хотите удалить элемент?")
                .setCancelable(true)
                .setPositiveButton("Да",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mPresenter.deleteData(mRecyclerView.getChildLayoutPosition(view));
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Нет",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
        return false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
        try {
            mListener = (OnItemSelectedFromTheList) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnItemSelectedFromTheList");
        }
    }

    @Override
    public void onDetach() {
        EventBus.getDefault().unregister(this);
        mListener = null;
        super.onDetach();
    }
}
