package com.thaiins.thaiinsurancecarinspection;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by MAN on 5/17/2017.
 */

public class MainApplication  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
