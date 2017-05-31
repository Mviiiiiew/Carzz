package com.thaiins.thaiinsurancecarinspection.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.thaiins.thaiinsurancecarinspection.R;
import com.thaiins.thaiinsurancecarinspection.dao.CarUserItemDao;
import com.thaiins.thaiinsurancecarinspection.fragment.MainFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.FragmentListner{
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainerMain, MainFragment.newInstance())
                    .commit();
        }

    }

    private void initInstances() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);


    }


    @Override
    public void onBackPressed() {
        finish();

    }

    @Override
    public void onCarItemClicked(CarUserItemDao dao) {
        Intent intent  = new Intent(MainActivity.this,CarDetailMainActivity.class);
        intent.putExtra("dao",dao);
        startActivity(intent);

    }
}
