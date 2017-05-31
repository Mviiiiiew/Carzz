package com.thaiins.thaiinsurancecarinspection.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.thaiins.thaiinsurancecarinspection.R;
import com.thaiins.thaiinsurancecarinspection.dao.CarUserItemDao;

import at.markushi.ui.CircleButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class CarDetailMainFragment extends Fragment implements View.OnClickListener {

    public interface FragmentListner {
        void onCarItemClickedCarMile();

        void onCarItemClickedCarRound();

        void onCarItemClickedCarFindLocation();


    }

    CarUserItemDao dao;
    TextView text_car_province;
    TextView text_car_model;
    TextView text_car_year;
    TextView text_car_license;
    ImageView iv_logo;
    CircleButton btn_capture_mile;
    CircleButton btn_capture_car_round;
    Button btn_find_location;


    public CarDetailMainFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static CarDetailMainFragment newInstance(CarUserItemDao dao) {
        CarDetailMainFragment fragment = new CarDetailMainFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao", dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        dao = getArguments().getParcelable("dao");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_car_detail_main, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        text_car_license = (TextView) rootView.findViewById(R.id.text_car_license);
        text_car_province = (TextView) rootView.findViewById(R.id.text_car_province);
        text_car_model = (TextView) rootView.findViewById(R.id.text_car_model);
        text_car_year = (TextView) rootView.findViewById(R.id.text_car_year);
        iv_logo = (ImageView) rootView.findViewById(R.id.iv_logo);
        btn_capture_mile = (CircleButton) rootView.findViewById(R.id.btn_capture_mile);
        btn_capture_car_round = (CircleButton) rootView.findViewById(R.id.btn_capture_car_round);
        btn_find_location = (Button) rootView.findViewById(R.id.btn_find_location);


        text_car_license.setText(dao.getCarLicense());
        text_car_model.setText(dao.getCarModel());
        text_car_province.setText(dao.getCarProvince());
        text_car_year.setText(dao.getCarYear());
        Glide.with(CarDetailMainFragment.this).load(dao.getCarLogo()).placeholder(R.drawable.loading).diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_logo);
        btn_capture_mile.setOnClickListener(this);
        btn_capture_car_round.setOnClickListener(this);
        btn_find_location.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here+

     *
     *
     *
     *
     *

     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    @Override
    public void onClick(View view) {
        FragmentListner listner = (FragmentListner) getActivity();
        if (view == btn_capture_mile) {
            listner.onCarItemClickedCarMile();
        } else if (view == btn_capture_car_round) {
            listner.onCarItemClickedCarRound();
        } else if(view == btn_find_location){
            listner.onCarItemClickedCarFindLocation();
        }
    }
}
