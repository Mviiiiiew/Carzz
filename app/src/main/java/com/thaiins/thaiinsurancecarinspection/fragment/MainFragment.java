package com.thaiins.thaiinsurancecarinspection.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.thaiins.thaiinsurancecarinspection.R;
import com.thaiins.thaiinsurancecarinspection.activity.CarDetailMainActivity;
import com.thaiins.thaiinsurancecarinspection.activity.LoginActivity;
import com.thaiins.thaiinsurancecarinspection.adapter.CarListAdapter;
import com.thaiins.thaiinsurancecarinspection.dao.CarUserItemDao;
import com.thaiins.thaiinsurancecarinspection.manager.CarUserListManager;
import com.thaiins.thaiinsurancecarinspection.manager.HttpManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MainFragment extends Fragment {
    //Variable
    public interface FragmentListner {
        void onCarItemClicked(CarUserItemDao dao);
    }

    ListView listView;
    final CarListAdapter adapter = new CarListAdapter();
    int queryText;
    SwipeRefreshLayout swipeRefreshLayout;
    Button btn_goTop;
    CarUserListManager carUserListManager;
    TextView Text_Empty;


    /*****************
     * Funtions
     ****************/
    private void showToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();

    }

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView);
        return rootView;
    }


    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        Text_Empty = (TextView) rootView.findViewById(R.id.Text_Empty);
        listView = (ListView) rootView.findViewById(R.id.listView);
        btn_goTop = (Button) rootView.findViewById(R.id.btn_goTop);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        btn_goTop.setOnClickListener(onClickListenerGoTop);
        listView.setEmptyView(Text_Empty);
        listView.setAdapter(adapter);
        setHasOptionsMenu(true);
        swipeRefreshLayout.setOnRefreshListener(refreshListener);
        reloadData();
        carUserListManager = new CarUserListManager();
        listView.setOnTouchListener(onTouchListenerListview);
        listView.setOnScrollListener(listViewScrollListener);
        listView.setOnItemClickListener(onItemClickListenerListview);


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        inflater.inflate(R.menu.search_main, menu);
        MenuItem item = menu.findItem(R.id.searcxh);
        final SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                queryText = query.length();
                hideButtonGoTop();
                adapter.getFilter().filter(query);


                return false;


            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                showToast("settings");
                break;
            case R.id.action_logout:
                showToast("Logout");
                break;

        }
        return super.onOptionsItemSelected(item);

    }

    private void reloadData() {
        swipeRefreshLayout.setRefreshing(false);


        Call<List<CarUserItemDao>> call = HttpManager.getInstance().getService().loadCarUser(1);
        call.enqueue(new Callback<List<CarUserItemDao>>() {
            @Override
            public void onResponse(Call<List<CarUserItemDao>> call, Response<List<CarUserItemDao>> response) {

                List<CarUserItemDao> dao = response.body();
                Log.d("dao", dao + "");
                carUserListManager.setDao(dao);
                adapter.setDao(carUserListManager.getDao());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<CarUserItemDao>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                showToast(t.toString());


            }
        });


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
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    public void showButtonGoTop() {

        btn_goTop.setVisibility(View.VISIBLE);
        //Animation anim = AnimationUtils.loadAnimation(Contextor.getInstance().getContext(), R.anim.zoom_fade_in);
        //btn_goTop.startAnimation(anim);

    }

    public void hideButtonGoTop() {
        btn_goTop.setVisibility(View.GONE);


    }


    /***************
     * Listener Zone
     ***************/
    SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            reloadData();
        }
    };

    AbsListView.OnScrollListener listViewScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView absListView, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            if (queryText > 0) {
                swipeRefreshLayout.setEnabled(false);
            } else {
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0);
                if (firstVisibleItem > 3) {
                    showButtonGoTop();
                } else {
                    hideButtonGoTop();
                }
            }

        }
    };

    View.OnClickListener onClickListenerGoTop = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            listView.smoothScrollToPosition(0, 0);
            hideButtonGoTop();

        }
    };

    View.OnTouchListener onTouchListenerListview = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(listView.getWindowToken(), 0);


            return false;

        }
    };

    AdapterView.OnItemClickListener onItemClickListenerListview = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


            CarUserItemDao dao = (CarUserItemDao) adapter.getDao().get(position);
            FragmentListner listner = (FragmentListner) getActivity();
            listner.onCarItemClicked(dao);


        }
    };


    /***************
     * Inner Class Zone
     ***************/


}
