package org.tensorflow.lite.examples.classification;

import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FileUploadUtils {
    public static void sendImage(File file) {
        Log.v("태그", "메시지");
        System.out.println(file);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("files", file.getName(), RequestBody.create(MultipartBody.FORM, file))
                .build();

        Request request = new Request.Builder()
                .url("http://bb6be6b454ce.ngrok.io/api/post")
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();
        Log.v("태그", "pass");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            //          Callback function to check data returned
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("TEST : ", response.body().string());
            }
        });
    }

    public static void sendText(String text) {
        Log.v("태그", "메시지");
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("files", file.getName(), RequestBody.create(MultipartBody.FORM, file))
//                .build();
        RequestBody requestBody = new FormBody.Builder()
                .add("key", text)
                .build();
        Request request = new Request.Builder()
//                .url("http://127.0.0.1:8000/app/hello/")

                .url("http://bb6be6b454ce.ngrok.io/api/post")
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        Log.v("태그", "pass");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            //          Callback function to check data returned
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("TEST : ", response.body().string());
            }
        });
    }
}
