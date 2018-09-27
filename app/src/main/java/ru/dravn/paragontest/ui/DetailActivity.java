package ru.dravn.paragontest.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.dravn.paragontest.R;

/**
 * Created by Jeka on 24.09.2018.
 */

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            DetailFragment fragment = (DetailFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_detail);
            fragment.setID(extras.getInt(EXTRA_ID));


        }
    }

}
