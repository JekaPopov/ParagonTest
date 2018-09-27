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
    public static final String FAVORITES ="Favorites";
    public static final String DISTRIBUTION = "3% distribution";

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void onItemSelect(Integer mPosition) {
        DetailFragment fragment = (DetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_detail);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setID(mPosition);
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_ID, mPosition);
            startActivity(intent);
        }
    }
}
