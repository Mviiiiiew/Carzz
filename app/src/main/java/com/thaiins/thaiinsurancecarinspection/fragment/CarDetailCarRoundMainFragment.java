package com.thaiins.thaiinsurancecarinspection.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.anton46.stepsview.StepsView;
import com.thaiins.thaiinsurancecarinspection.R;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class CarDetailCarRoundMainFragment extends Fragment implements View.OnClickListener {
StepsView stepsView;
    Button btn_Next;
    Button btn_Back;

    private final String[] TextStep = {"Step 1", "Step 2", "Step 3", "Step 4", "Step 5","Step 6"};

    public CarDetailCarRoundMainFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static CarDetailCarRoundMainFragment newInstance() {
        CarDetailCarRoundMainFragment fragment = new CarDetailCarRoundMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_car_detail_car_round_main, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here

    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        stepsView = (StepsView)rootView.findViewById(R.id.stepsView);
        btn_Back = (Button) rootView.findViewById(R.id.btn_Back);
        btn_Next = (Button)rootView.findViewById(R.id.btn_Next);

        btn_Back.setVisibility(View.INVISIBLE);
        stepsView.setLabels(TextStep).setCompletedPosition(0)
                .setProgressColorIndicator(getContext().getResources().getColor(R.color.colorBlue))
        .setBarColorIndicator(getResources().getColor(R.color.colorGrey))
        .setLabelColorIndicator(getResources().getColor(R.color.colorBlack))
        .drawView();


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
     * Save Instance State Here
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

    }
}
