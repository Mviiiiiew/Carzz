package com.thaiins.thaiinsurancecarinspection.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MAN on 5/17/2017.
 */

public class CarUserItemDao implements Parcelable {
    @SerializedName("car_id")
    @Expose
    private Integer carId;
    @SerializedName("customre_id")
    @Expose
    private Integer customreId;
    @SerializedName("car_license")
    @Expose
    private String carLicense;
    @SerializedName("car_model")
    @Expose
    private String carModel;
    @SerializedName("car_logo")
    @Expose
    private String carLogo;
    @SerializedName("car_province")
    @Expose
    private String carProvince;
    @SerializedName("car_year")
    @Expose
    private String carYear;

    public CarUserItemDao(String carLicense, String carLogo, String carModel, String carProvince, String carYear) {
        this.carLicense = carLicense;
        this.carLogo = carLogo;
        this.carModel = carModel;
        this.carProvince = carProvince;
        this.carYear = carYear;



    }

    public CarUserItemDao (){

    }

    protected CarUserItemDao(Parcel in) {
        carLicense = in.readString();
        carModel = in.readString();
        carLogo = in.readString();
        carProvince = in.readString();
        carYear = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(carLicense);
        dest.writeString(carModel);
        dest.writeString(carLogo);
        dest.writeString(carProvince);
        dest.writeString(carYear);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CarUserItemDao> CREATOR = new Creator<CarUserItemDao>() {
        @Override
        public CarUserItemDao createFromParcel(Parcel in) {
            return new CarUserItemDao(in);
        }

        @Override
        public CarUserItemDao[] newArray(int size) {
            return new CarUserItemDao[size];
        }
    };

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCustomreId() {
        return customreId;
    }

    public void setCustomreId(Integer customreId) {
        this.customreId = customreId;
    }

    public String getCarLicense() {
        return carLicense;
    }

    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarLogo() {
        return carLogo;
    }

    public void setCarLogo(String carLogo) {
        this.carLogo = carLogo;
    }

    public String getCarProvince() {
        return carProvince;
    }

    public void setCarProvince(String carProvince) {
        this.carProvince = carProvince;
    }

    public String getCarYear() {
        return carYear;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }
}
