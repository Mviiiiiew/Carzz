package com.thaiins.thaiinsurancecarinspection.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.thaiins.thaiinsurancecarinspection.R;
import com.thaiins.thaiinsurancecarinspection.manager.HttpManager;
import com.thaiins.thaiinsurancecarinspection.util.ProgressRequestBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class CarDetailMileMainFragment extends Fragment implements View.OnClickListener ,ProgressRequestBody.UploadCallbacks{
    Button btn_upload;
    Button btn_browse;
    ImageView iv_image;
    private Uri mImageUri;
    private Uri mImageUri1;
    ProgressDialog progressDoalog;
    int countImage = 1;


    Button btn_browse1;
    ImageView iv_image1;





    private void showToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();

    }


    public CarDetailMileMainFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static CarDetailMileMainFragment newInstance() {
        CarDetailMileMainFragment fragment = new CarDetailMileMainFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_car_detail_mile_main, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_browse = (Button) rootView.findViewById(R.id.btn_browse);
        btn_upload = (Button) rootView.findViewById(R.id.btn_upload);
        iv_image = (ImageView) rootView.findViewById(R.id.iv_image);
        iv_image1 = (ImageView) rootView.findViewById(R.id.iv_image1);
        btn_browse1 = (Button) rootView.findViewById(R.id.btn_browse1);


        btn_browse.setOnClickListener(this);
        btn_upload.setOnClickListener(this);
        btn_browse1.setOnClickListener(this);



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

        String name ;
        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMax(100);
        progressDoalog.setTitle("UpLoad Image");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDoalog.setMessage("Wait");




        if (btn_browse == view) {
            imageCapture();
            showToast("Capture");

        } else if (btn_upload == view) {
            uploadImage(mImageUri);
            progressDoalog.show();


        }else if(btn_browse1 == view){
            imageCapture1();
            showToast("Capture1");
        }


    }

    private void uploadImage(Uri uri ) {

        File file = new File(uri.getPath());


        Log.d("UriTestxx",uri+"");
        Log.d("UriTest",file+"");
       ProgressRequestBody fileBody = new ProgressRequestBody(file, this);
        //RequestBody mFilePath = RequestBody.create(MediaType.parse("image/jpg"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), fileBody);


        // show it


        Call<ResponseBody> call = HttpManager.getInstance().getService().postImage(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                progressDoalog.dismiss();


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showToast("NO");
                progressDoalog.dismiss();

            }
        });

    }


    /*private void uploadImage1(Uri uri,Uri uri1 ) {

        File file = new File(uri.getPath());
        File file1 = new File(uri1.getPath());


        Log.d("UriTestxx",uri+"");
        Log.d("UriTest",file+"");
       ProgressRequestBody fileBody = new ProgressRequestBody(file, this);
     // RequestBody mFilePath = RequestBody.create(MediaType.parse("image/jpg"), file);
        ProgressRequestBody fileBody1 = new ProgressRequestBody(file1, this);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), fileBody);
        MultipartBody.Part body1 = MultipartBody.Part.createFormData("file", file1.getName(), fileBody1);

        // show it
        progressDoalog.show();

        Call<ResponseBody> call = HttpManager.getInstance().getService().postImage2(body,body1);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();

                progressDoalog.dismiss();


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showToast("NO");
                progressDoalog.dismiss();

            }
        });

    }

*/
    private void imageCapture() {
        File extDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File myPicDir = new File(extDir.getAbsolutePath() + "/MyPicture");
        if (!myPicDir.exists()) {
            myPicDir.mkdir();
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = simpleDateFormat.format(date) + ".jpg";
        File  imgFileOne = new File(myPicDir + "/" + fileName);
        mImageUri = Uri.fromFile(imgFileOne);


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
        startActivityForResult(intent, 111);

    }

    private void imageCapture1() {
        File extDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File myPicDir = new File(extDir.getAbsolutePath() + "/MyPicture");
        if (!myPicDir.exists()) {
            myPicDir.mkdir();
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = simpleDateFormat.format(date) + ".jpg";
        File  imgFileOne = new File(myPicDir + "/" + fileName);
        mImageUri1 = Uri.fromFile(imgFileOne);


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri1);
        startActivityForResult(intent, 222);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == getActivity().RESULT_OK) {


            String path = mImageUri.getPath();
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            File file = new File(mImageUri.getPath());
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            iv_image.setImageBitmap(bitmap);


        }else if (requestCode == 222 && resultCode == getActivity().RESULT_OK){
            String path = mImageUri1.getPath();
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            File file = new File(mImageUri1.getPath());
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            iv_image1.setImageBitmap(bitmap);

        }


    }

    @Override
    public void onProgressUpdate(int percentage) {

            progressDoalog.setProgress(percentage);
        Log.d("ProgressBarz",percentage+"");
        //showToast("sdafsdfsdf");



    }

    @Override
    public void onError() {
        showToast("NO");
    }

    @Override
    public void onFinish() {
        progressDoalog.setProgress(100);
        showToast("sdafsdfsdf");



    }
}
