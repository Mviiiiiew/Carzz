package com.thaiins.thaiinsurancecarinspection.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.thaiins.thaiinsurancecarinspection.R;
import com.thaiins.thaiinsurancecarinspection.dao.CarUserItemDao;
import com.thaiins.thaiinsurancecarinspection.fragment.CarDetailMainFragment;
import com.thaiins.thaiinsurancecarinspection.fragment.CarFindLocationMainFragment;

public class CarDetailMainActivity extends AppCompatActivity implements CarDetailMainFragment.FragmentListner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail_main);

        CarUserItemDao dao = getIntent().getParcelableExtra("dao");


        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainerCarDetailMain, CarDetailMainFragment.newInstance(dao))
                    .commit();
        }
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



    @Override
    public void onCarItemClickedCarMile() {
        Intent intent  = new Intent(CarDetailMainActivity.this,CarDetailMileMainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onCarItemClickedCarRound() {
        Intent intent  = new Intent(CarDetailMainActivity.this,CarDetailCarRoundMainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCarItemClickedCarFindLocation() {
        Intent intent  = new Intent(CarDetailMainActivity.this,CarFindLocationMainActivity.class);
        startActivity(intent);
    }
}
