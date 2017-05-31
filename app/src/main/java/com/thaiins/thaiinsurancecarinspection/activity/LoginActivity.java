package com.thaiins.thaiinsurancecarinspection.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thaiins.thaiinsurancecarinspection.R;
import com.thaiins.thaiinsurancecarinspection.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initInstances();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainerLogin, LoginFragment.newInstance())
                    .commit();
        }

    }

    private void initInstances() {




    }
}
