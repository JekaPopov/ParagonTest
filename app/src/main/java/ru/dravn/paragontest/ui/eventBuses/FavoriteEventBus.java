package ru.dravn.paragontest.ui.eventBuses;

import ru.dravn.paragontest.db.model.DataModel;

/**
 * Created by Jeka on 25.09.2018.
 */

public class FavoriteEventBus {

    public final DataModel mFavorite;

    public FavoriteEventBus(DataModel favorite) {
        this.mFavorite = favorite;
    }
}