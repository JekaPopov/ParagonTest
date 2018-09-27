package ru.dravn.paragontest.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import ru.dravn.paragontest.R;
import ru.dravn.paragontest.db.model.DataModel;
import ru.dravn.paragontest.ui.eventBuses.FavoriteEventBus;

/**
 * Created by Jeka on 24.09.2018.
 */

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<DataModel> mDataModels = new ArrayList<>();
    private View.OnClickListener mOnClickListener;
    private View.OnLongClickListener mOnLongClickListener;

    private int mSelectedPos = RecyclerView.NO_POSITION;

    DataAdapter(Context context, List<DataModel> dataModels) {
        this.mDataModels = dataModels;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        mOnLongClickListener = onLongClickListener;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.list_item, parent, false);
        view.setOnClickListener(mOnClickListener);
        view.setOnLongClickListener(mOnLongClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, final int position) {
        holder.mCardView.setCardBackgroundColor(mSelectedPos == position ?
                mContext.getResources().getColor(R.color.selectItem)
                : mContext.getResources().getColor(R.color.unSelectItem));

        holder.mNameView
                .setText(String.format(mContext.getString(R.string.name), mDataModels.get(position).getName()));
        holder.mVersionView
                .setText(String.format(mContext.getString(R.string.version), mDataModels.get(position).getVersion()));
        holder.mFavoriteView
                .setImageResource(mDataModels.get(position).getFavorite() ?
                        R.drawable.ic_favorite : R.drawable.ic_no_favorite);

        holder.mFavoriteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new FavoriteEventBus(mDataModels.get(position)));
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataModels.size();
    }

    public int getSelectedPos() {
        return mSelectedPos;
    }

    public void setSelectedPos(int selectedPos) {
        mSelectedPos = selectedPos;
    }


    public void setData(List<DataModel> data) {
        mDataModels = data;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mFavoriteView;
        TextView mNameView, mVersionView;
        CardView mCardView;

        ViewHolder(View view) {
            super(view);
            mCardView = view.findViewById(R.id.cardView);
            mFavoriteView = view.findViewById(R.id.favorite);
            mNameView = view.findViewById(R.id.name);
            mVersionView = view.findViewById(R.id.version);
        }
    }
}