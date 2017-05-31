package com.thaiins.thaiinsurancecarinspection.manager.http;

import com.thaiins.thaiinsurancecarinspection.dao.CarUserItemDao;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by MAN on 5/17/2017.
 */

public interface ApiService {

    @GET("cars")
    Call<List<CarUserItemDao>> loadCarUser( @Query("cust_id") int cust_id);


    @Multipart
    @POST("fileupload")
    Call<ResponseBody> postImage(@Part MultipartBody.Part image);

    @Multipart
    @POST("Multiplefileupload")
    Call<ResponseBody> postImage2(@Part MultipartBody.Part image1,
                                  @Part MultipartBody.Part image2);

}
