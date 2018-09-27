/*
 * Author: Popov Evgeniy
 *
 */
package ru.dravn.paragontest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;

import ru.dravn.paragontest.R;
import ru.dravn.paragontest.ui.eventBuses.FilterEventBus;

public class MainActivity extends AppCompatActivity implements OnItemSelectedFromTheList {

    public static final String ALL = "All";
    public static final String FAVORITES = "Favorites";
    public static final String DISTRIBUTION = "3% distribution";
    public static final int NO_ID = -1;
    public static final String ID = "Id";

    private Toolbar mToolbar;

    private Integer mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mId = NO_ID;
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.all);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.all:
                mToolbar.setTitle(R.string.all);
                EventBus.getDefault().post(new FilterEventBus(ALL));
                return true;
            case R.id.favorites:
                mToolbar.setTitle(R.string.favorites);
                EventBus.getDefault().post(new FilterEventBus(FAVORITES));
                return true;
            case R.id.distribution:
                EventBus.getDefault().post(new FilterEventBus(DISTRIBUTION));
                mToolbar.setTitle(R.string.persent_distribution);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onItemSelect(Integer id) {
        mId = id;

        DetailFragment fragment = (DetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_detail);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setId(id);
        } else if (id != MainActivity.NO_ID) {
            Intent intent = new Intent(getApplicationContext(),
                    DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_ID, id);
            startActivity(intent);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(ID, mId);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mId = savedInstanceState.getInt(ID, NO_ID);

        if (mId != NO_ID) {
            MasterFragment masterFragment = (MasterFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_master);
            if (masterFragment != null && masterFragment.isInLayout()) {
                masterFragment.setId(mId);
            }

            DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_detail);
            if (detailFragment != null && detailFragment.isInLayout()) {
                detailFragment.setId(mId);
            }
        }
    }
}
