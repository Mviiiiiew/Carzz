package com.thaiins.thaiinsurancecarinspection.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;

import android.location.LocationManager;

import android.os.Bundle;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.thaiins.thaiinsurancecarinspection.R;
import com.thaiins.thaiinsurancecarinspection.dao.MapsDao;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class CarFindLocationMainFragment extends Fragment implements View.OnClickListener {
    private MapFragment mapFragment;
    private GoogleApiClient mGoogleApi;
    FloatingActionButton btn_find_location;
    double mLastlat = 13.00;
    double mLastlon = 100.00;


    LocationListener listener;
    LocationManager locationManager;
    TextView textView1;
    TextView textView2;
    String maps;


    private void showToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();

    }

    public CarFindLocationMainFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static CarFindLocationMainFragment newInstance() {
        CarFindLocationMainFragment fragment = new CarFindLocationMainFragment();
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
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_car_find_location_main, container, false);
        mGoogleApi = new GoogleApiClient.Builder(getActivity()).addApi(LocationServices.API).addConnectionCallbacks(mCallback).addOnConnectionFailedListener(mOnFailed).build();
        {
        }
        initInstances(rootView, savedInstanceState);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng pos = new LatLng(mLastlat, mLastlon);
                MarkerOptions markerOptions = new MarkerOptions().position(pos).title("Title").snippet("Snippet");
                googleMap.addMarker(markerOptions);

                //UI


                CameraPosition position = CameraPosition.builder().target(pos).zoom(12).bearing(30).tilt(0).build();
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));

            }
        });


        return rootView;
    }

    private void Check() {
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            Log.d("gps_enabled", gps_enabled + "");
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            Log.d("network_enabled", network_enabled + "");
        } catch (Exception ex) {

        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setMessage("Please Enable LOCATION GPS");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    getActivity().startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            dialog.show();
        }
    }


    private GoogleApiClient.ConnectionCallbacks mCallback = new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(@Nullable Bundle bundle) {
            LocationRequest request = new LocationRequest()
                    .setInterval(5000).setFastestInterval(2000).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            try {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApi, request, locationListener);

            } catch (SecurityException ex) {
            }


        }

        @Override
        public void onConnectionSuspended(int i) {
            LocationRequest request = new LocationRequest()
                    .setInterval(5000).setFastestInterval(2000).setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

            try {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApi, request, locationListener);

            } catch (SecurityException ex) {
            }
        }
    };

    private GoogleApiClient.OnConnectionFailedListener mOnFailed = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        }
    };


    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            if (lat != mLastlat && lon != mLastlon) {
                textView1.append("\n" + lat);
                textView2.append("\n" + lon);

                mLastlat = lat;
                mLastlon = lon;

            }


        }


    };


    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

        btn_find_location = (FloatingActionButton) rootView.findViewById(R.id.btn_find_location);
        textView1 = (TextView) rootView.findViewById(R.id.textView1);
        textView2 = (TextView) rootView.findViewById(R.id.textView2);
        btn_find_location.setOnClickListener(this);

        mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.mapFragment);

    }

    @Override
    public void onStart() {
        if (mGoogleApi != null) {
            mGoogleApi.connect();
            showToast("Connect");

        }

        super.onStart();


    }

    @Override
    public void onStop() {
        if (mGoogleApi != null && mGoogleApi.isConnected()) {
            mGoogleApi.disconnect();
            showToast("Fail");
        }

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
        if (btn_find_location == view) {
            Check();
            maps = "https://www.google.co.th/maps/place/" + mLastlat + "," + mLastlon;
            MapsDao mapsDao = new MapsDao();
            mapsDao.setLat(mLastlat);
            mapsDao.setLon(mLastlon);
            showToast(mapsDao.getLat()+mapsDao.getLon()+"");

            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    LatLng pos = new LatLng(mLastlat, mLastlon);


                    MarkerOptions markerOptions = new MarkerOptions().position(pos).title("Title").snippet("Snippet");
                    googleMap.addMarker(markerOptions);

                    //UI


                    CameraPosition position = CameraPosition.builder().target(pos).zoom(17).bearing(30).tilt(0).build();
                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));

                }
            });


        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_find_location, menu);
        MenuItem item = (MenuItem) menu.findItem(R.id.action_share);
        ShareActionProvider shareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(item);
        shareActionProvider.setShareIntent(getShareIntent());

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                showToast("Share");
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    private Intent getShareIntent() {
        MapsDao mapsDao = new MapsDao();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "https://www.google.co.th/maps/place/" + mapsDao.getLat() + mapsDao.getLon());

        return intent;

    }


}
