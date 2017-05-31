package com.thaiins.thaiinsurancecarinspection.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.thaiins.thaiinsurancecarinspection.R;
import com.thaiins.thaiinsurancecarinspection.fragment.CarFindLocationMainFragment;

public class CarFindLocationMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_find_location_main);


        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainerCarFindLocation, CarFindLocationMainFragment.newInstance())
                    .commit();
        }
        initInstances();
    }

    private void initInstances() {

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== android.R.id.home){
            finish();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

}
