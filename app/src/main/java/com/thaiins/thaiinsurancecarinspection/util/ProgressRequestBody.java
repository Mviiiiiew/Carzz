package com.thaiins.thaiinsurancecarinspection.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by MAN on 5/29/2017.
 */

public class ProgressRequestBody extends RequestBody {
    private File mFile;
    private UploadCallbacks mListener;


    private static final int DEFAULT_BUFFER_SIZE = 2048;

    public interface UploadCallbacks {
        void onProgressUpdate(int percentage);

        void onError();

        void onFinish();
    }

    public ProgressRequestBody(final File file, final UploadCallbacks listener) {
        mFile = file;
        mListener = listener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return MediaType.parse("*/*");
    }

    @Override
    public long contentLength() throws IOException {
        return mFile.length();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

        long fileLength = mFile.length();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        ArrayList<File> files = new ArrayList<File>();
        files.add(mFile);
        files.add(mFile);
        for (File file : files) {
            file.length();
            file.getPath();

            Log.d("filelength",   file.length() + "");
            Log.d("filelength",   file.getPath() + "");
        }

        FileInputStream in = new FileInputStream(mFile);
        // InputStream in = in();
        long uploaded = 0;

            try {
                int read;
                Handler handler = new Handler(Looper.getMainLooper());
                int num = 0;
                while ((read = in.read(buffer)) != -1) {

                    int progress = (int) (100 * uploaded / fileLength);
                    if (progress > num + 1) {
                        // update progress on UI thread
                        handler.post(new ProgressUpdater(uploaded, fileLength));
                        num = progress;
                    }

                    uploaded += read;
                    sink.write(buffer, 0, read);
                }
            } finally {
                in.close();
            }

    }

    private class ProgressUpdater implements Runnable {
        private long mUploaded;
        private long mTotal;

        public ProgressUpdater(long uploaded, long total) {
            mUploaded = uploaded;
            mTotal = total;
            Log.d("mUploaded", mUploaded + "");
            Log.d("mTotal", mTotal + "");
        }


        @Override
        public void run() {
            mListener.onProgressUpdate((int) (100 * mUploaded / mTotal));

        }
    }
}
