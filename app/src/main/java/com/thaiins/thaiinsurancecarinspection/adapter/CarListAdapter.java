package com.thaiins.thaiinsurancecarinspection.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Filterable;

import com.thaiins.thaiinsurancecarinspection.R;
import com.thaiins.thaiinsurancecarinspection.dao.CarUserItemDao;
import com.thaiins.thaiinsurancecarinspection.view.CarListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

/**
 * Created by MAN on 5/17/2017.
 */

public class CarListAdapter extends BaseAdapter implements Filterable {

    List<CarUserItemDao> dao;
    List<CarUserItemDao> filterList;
    int lastPosition = -1;
    CustomFilter filter;


    public void setDao(List<CarUserItemDao> dao) {
        this.dao = dao;
        this.filterList = dao;


    }

    public List<CarUserItemDao> getDao() {
        return dao;
    }

    @Override
    public int getCount() {
        if (dao == null)
            return 0;

        return dao.size();
    }

    @Override
    public Object getItem(int position) {
        return dao.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CarListItem item;
        if (convertView != null)
            item = (CarListItem) convertView;
        else
            item = new CarListItem(parent.getContext());

        CarUserItemDao dao = (CarUserItemDao) getItem(position);
        item.setLicenseCar(dao.getCarLicense().toString());
        item.setProvinceCar(dao.getCarProvince().toString());
        item.setModelCar(dao.getCarModel().toString());
        item.setYearCar(dao.getCarYear());
        item.setImageUrl(dao.getCarLogo());
        if (position > lastPosition) {

            Animation anim = AnimationUtils.loadAnimation(parent.getContext(),
                    R.anim.up_from_botton);
            item.startAnimation(anim);
            lastPosition = position;

        }

        return item;
    }

    @Override
    public android.widget.Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter();
        }
        return filter;
    }

    private class CustomFilter extends android.widget.Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString().toUpperCase();

                ArrayList<CarUserItemDao> filters = new ArrayList<>();
                for (int i = 0; i < filterList.size(); i++) {
                    if (filterList.get(i).getCarLicense().toUpperCase().contains(constraint)) {
                        CarUserItemDao u = new CarUserItemDao(filterList.get(i).getCarLicense()
                                , filterList.get(i).getCarLogo(),
                                filterList.get(i).getCarModel(), filterList.get(i).getCarProvince(),
                                filterList.get(i).getCarYear()
                        );
                        filters.add(u);
                    }
                }
                results.count = filters.size();
                results.values = filters;
            } else {
                results.count = filterList.size();
                results.values = filterList;
            }


            return results;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {

            dao = (ArrayList<CarUserItemDao>) results.values;

            notifyDataSetChanged();
        }
    }
}
