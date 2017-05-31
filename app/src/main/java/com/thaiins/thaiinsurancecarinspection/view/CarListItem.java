package com.thaiins.thaiinsurancecarinspection.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;
import com.thaiins.thaiinsurancecarinspection.R;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class CarListItem extends BaseCustomViewGroup {
    TextView text_car_license;
    TextView text_car_province;
    TextView text_car_model;
    TextView text_car_year;
    ImageView iv_logo;

    public CarListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public CarListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public CarListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public CarListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_item_car, this);
    }

    private void initInstances() {
        // findViewById here
        text_car_license = (TextView) findViewById(R.id.text_car_license);
        text_car_province = (TextView) findViewById(R.id.text_car_province);
        text_car_model = (TextView) findViewById(R.id.text_car_model);
        text_car_year = (TextView) findViewById(R.id.text_car_year);
        iv_logo = (ImageView) findViewById(R.id.iv_logo);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }
    public void setLicenseCar(String text) {
        text_car_license.setText(text);
    }
    public void setProvinceCar(String text) {
        text_car_province.setText(text);
    }
    public void setModelCar(String text) {
        text_car_model.setText(text);
    }
    public void setYearCar(String text) {
        text_car_year.setText(text);
    }
    public void setImageUrl(String url) {
        Glide.with(getContext()).load(url).placeholder(R.drawable.loading)
                .into(iv_logo);

    }

}
