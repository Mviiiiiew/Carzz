package com.thaiins.thaiinsurancecarinspection.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.thaiins.thaiinsurancecarinspection.dao.CarUserItemDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class CarUserListManager {


    private Context mContext;
    private List<CarUserItemDao> dao;


    public List<CarUserItemDao> getDao() {
        return dao;

    }

    public void setDao(List<CarUserItemDao> dao) {
        this.dao = dao;

}

    public CarUserListManager() {
        mContext = Contextor.getInstance().getContext();


    }





}
