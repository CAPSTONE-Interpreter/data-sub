package org.tensorflow.lite.examples.classification;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

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

import static org.tensorflow.lite.examples.classification.TextSearch.urls;


public class FileUploadUtils {
    public static void sendImage(File file) {
        Log.v("태그", "메시지");
        System.out.println(file);

//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("files", file.getName(), RequestBody.create(MultipartBody.FORM, file))
//                .build();
//
//        Request request = new Request.Builder()
//                .url("http://ec2-3-36-221-249.ap-northeast-2.compute.amazonaws.com:8080/test")
//                .post(requestBody)
//                .build();

        Request request = new Request.Builder()
                .url("http://ec2-3-36-221-249.ap-northeast-2.compute.amazonaws.com:8080/videos?text=하늘")
//                .post(requestBody):
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
        Log.v("called", text);
        Request request = new Request.Builder()
                .url("http://ec2-3-36-221-249.ap-northeast-2.compute.amazonaws.com:8080/videos?text="+text)
//                .post(requestBody):q
                .build();
        OkHttpClient client = new OkHttpClient();
        Log.v("태그", client.toString());
        Log.v("태그", text);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            //          Callback function to check data returned
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String strJsonOutput = response.body().string();
                Log.d("TEST : ", strJsonOutput);
                urls.add(new URL("소나무, 솔", "https://sldict.korean.go.kr/multimedia/multimedia_files/convert/20160107/241975/MOV000254400_700X466.webm"));
                urls.add(new URL("나무, 수목", "https://sldict.korean.go.kr/multimedia/multimedia_files/convert/20191007/624819/MOV000248680_700X466.webm"));
                Log.d("text : ", urls.get(0).text);
                Log.d("url : ", urls.get(0).url);
                Log.d("text : ", urls.get(1).text);
                Log.d("url : ", urls.get(1).url);
            }
        });
    }

    public static void sendTextMotion(String text) {
        Log.v("called", text);
        Request request = new Request.Builder()
                .url("http://ec2-3-36-221-249.ap-northeast-2.compute.amazonaws.com:8080/translate?word="+text)
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
