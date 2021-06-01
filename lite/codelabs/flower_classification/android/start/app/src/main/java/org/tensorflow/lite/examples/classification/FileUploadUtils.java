package org.tensorflow.lite.examples.classification;

import android.graphics.Camera;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
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

import static android.os.Looper.getMainLooper;
import static org.tensorflow.lite.examples.classification.TextSearch.urls;
import static org.tensorflow.lite.examples.classification.CameraActivity.addText;
import static org.tensorflow.lite.examples.classification.TextSearch.num_page;
import static org.tensorflow.lite.examples.classification.PhotoSearch.num_page2;
import static org.tensorflow.lite.examples.classification.PhotoSearch.image_urls;


public class FileUploadUtils {
    public static void sendImage(File file) {
        Log.v("태그", "메시지");
        System.out.println(file);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MultipartBody.FORM, file))
                .build();

        Request request = new Request.Builder()
                .url("http://ec2-3-36-221-249.ap-northeast-2.compute.amazonaws.com:8080/videos/photo")
//                .url("http://ec2-3-36-221-249.ap-northeast-2.compute.amazonaws.com:8080/test")
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
                final String strJsonOutput = response.body().string();
                urls.clear();
                Log.d("photo: ", strJsonOutput);
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(strJsonOutput);
                    Log.d("photo2: ", jsonArray.getJSONObject(0).getString("result"));

                    String strJsonOutput2 = jsonArray.getJSONObject(0).getString("result");
                    JSONArray jsonArray2 = new JSONArray(strJsonOutput2);
                    String strJsonOutput3 = jsonArray.getJSONObject(1).getString("result");
                    JSONArray jsonArray3 = new JSONArray(strJsonOutput3);
                    String strJsonOutput4 = jsonArray.getJSONObject(2).getString("result");
                    JSONArray jsonArray4 = new JSONArray(strJsonOutput4);
                    String strJsonOutput5 = jsonArray.getJSONObject(3).getString("result");
                    JSONArray jsonArray5 = new JSONArray(strJsonOutput5);
                    String strJsonOutput6 = jsonArray.getJSONObject(4).getString("result");
                    JSONArray jsonArray6 = new JSONArray(strJsonOutput6);

                    Log.d("photo3: ", jsonArray2.getJSONObject(0).getString("title"));
                    Log.d("photo3: ", jsonArray2.getJSONObject(0).getString("url"));
                    Log.d("photo3: ", jsonArray2.getJSONObject(0).getString("title"));
                    Log.d("photo3: ", jsonArray3.getJSONObject(0).getString("url"));
                    Log.d("photo3: ", jsonArray2.getJSONObject(0).getString("title"));
                    Log.d("photo3: ", jsonArray4.getJSONObject(0).getString("url"));
                    Log.d("photo3: ", jsonArray2.getJSONObject(0).getString("title"));
                    Log.d("photo3: ", jsonArray5.getJSONObject(0).getString("url"));
                    Log.d("photo3: ", jsonArray2.getJSONObject(0).getString("title"));
                    Log.d("photo3: ", jsonArray6.getJSONObject(0).getString("url"));

                    Log.d("length: ", Integer.toString(jsonArray.length()));
//                    num_page2 = jsonArray.length();

                    urls.add(new URL(jsonArray2.getJSONObject(0).getString("title"), jsonArray2.getJSONObject(0).getString("url")));
                    urls.add(new URL(jsonArray3.getJSONObject(0).getString("title"), jsonArray3.getJSONObject(0).getString("url")));
                    urls.add(new URL(jsonArray4.getJSONObject(0).getString("title"), jsonArray4.getJSONObject(0).getString("url")));
                    urls.add(new URL(jsonArray5.getJSONObject(0).getString("title"), jsonArray5.getJSONObject(0).getString("url")));
                    urls.add(new URL(jsonArray6.getJSONObject(0).getString("title"), jsonArray6.getJSONObject(0).getString("url")));
                    Log.d("TEST : ", strJsonOutput);
                    Log.d("text : ", urls.get(0).text);
                    Log.d("url : ", urls.get(0).url);
                    Log.d("text : ", urls.get(1).text);
                    Log.d("url : ", urls.get(1).url);
                    Log.d("text : ", urls.get(2).text);
                    Log.d("url : ", urls.get(2).url);
                    Log.d("text : ", urls.get(3).text);
                    Log.d("url : ", urls.get(3).url);
                    Log.d("text : ", urls.get(4).text);
                    Log.d("url : ", urls.get(4).url);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                try {
//                    JSONArray jsonArray = new JSONArray(strJsonOutput);
//                    urls.add(new URL(jsonArray.getJSONObject(0).getString("title"), jsonArray.getJSONObject(0).getString("url")));
//                    urls.add(new URL(jsonArray.getJSONObject(1).getString("title"), jsonArray.getJSONObject(1).getString("url")));
//                    urls.add(new URL(jsonArray.getJSONObject(2).getString("title"), jsonArray.getJSONObject(2).getString("url")));
//                    urls.add(new URL(jsonArray.getJSONObject(3).getString("title"), jsonArray.getJSONObject(3).getString("url")));
//                    urls.add(new URL(jsonArray.getJSONObject(4).getString("title"), jsonArray.getJSONObject(4).getString("url")));
//                    Log.d("TEST : ", strJsonOutput);
//                    Log.d("text : ", urls.get(0).text);
//                    Log.d("url : ", urls.get(0).url);
//                    Log.d("text : ", urls.get(1).text);
//                    Log.d("url : ", urls.get(1).url);
//                    Log.d("text : ", urls.get(2).text);
//                    Log.d("url : ", urls.get(2).url);
//                    Log.d("text : ", urls.get(3).text);
//                    Log.d("url : ", urls.get(3).url);
//                    Log.d("text : ", urls.get(4).text);
//                    Log.d("url : ", urls.get(4).url);
////                    JSONArray jsonArray = new JSONArray(strJsonOutput);
////                    image_urls.add(new URL(jsonArray.getJSONObject(0).getString("title"), jsonArray.getJSONObject(0).getString("url")));
////                    image_urls.add(new URL(jsonArray.getJSONObject(1).getString("title"), jsonArray.getJSONObject(1).getString("url")));
////                    image_urls.add(new URL(jsonArray.getJSONObject(2).getString("title"), jsonArray.getJSONObject(2).getString("url")));
////                    image_urls.add(new URL(jsonArray.getJSONObject(3).getString("title"), jsonArray.getJSONObject(3).getString("url")));
////                    image_urls.add(new URL(jsonArray.getJSONObject(4).getString("title"), jsonArray.getJSONObject(4).getString("url")));
////                    Log.d("TEST : ", strJsonOutput);
////                    Log.d("text : ", image_urls.get(0).text);
////                    Log.d("url : ", image_urls.get(0).url);
////                    Log.d("text : ", image_urls.get(1).text);
////                    Log.d("url : ", image_urls.get(1).url);
////                    Log.d("text : ", image_urls.get(2).text);
////                    Log.d("url : ", image_urls.get(2).url);
////                    Log.d("text : ", image_urls.get(3).text);
////                    Log.d("url : ", image_urls.get(3).url);
////                    Log.d("text : ", image_urls.get(4).text);
////                    Log.d("url : ", image_urls.get(4).url);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    public static void sendText(String text) {
        Log.v("called", text);
        Request request = new Request.Builder()
                .url("http://ec2-3-36-221-249.ap-northeast-2.compute.amazonaws.com:8080/videos?text=" + text)
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
                urls.clear();

                try {
                    JSONArray jsonArray = new JSONArray(strJsonOutput);
                    Log.d("length: ", Integer.toString(jsonArray.length()));
//                    num_page = jsonArray.length();
                    urls.add(new URL(jsonArray.getJSONObject(0).getString("title"), jsonArray.getJSONObject(0).getString("url")));
                    urls.add(new URL(jsonArray.getJSONObject(1).getString("title"), jsonArray.getJSONObject(1).getString("url")));
                    urls.add(new URL(jsonArray.getJSONObject(2).getString("title"), jsonArray.getJSONObject(2).getString("url")));
                    urls.add(new URL(jsonArray.getJSONObject(3).getString("title"), jsonArray.getJSONObject(3).getString("url")));
                    urls.add(new URL(jsonArray.getJSONObject(4).getString("title"), jsonArray.getJSONObject(4).getString("url")));
                    Log.d("TEST : ", strJsonOutput);
                    Log.d("text : ", urls.get(0).text);
                    Log.d("url : ", urls.get(0).url);
                    Log.d("text : ", urls.get(1).text);
                    Log.d("url : ", urls.get(1).url);
                    Log.d("text : ", urls.get(2).text);
                    Log.d("url : ", urls.get(2).url);
                    Log.d("text : ", urls.get(3).text);
                    Log.d("url : ", urls.get(3).url);
                    Log.d("text : ", urls.get(4).text);
                    Log.d("url : ", urls.get(4).url);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void sendTextMotion(String text) {
        final String[] resultText = new String[1];
        Log.v("called", text);
        final Request request = new Request.Builder()
                .url("http://ec2-3-36-221-249.ap-northeast-2.compute.amazonaws.com:8080/translate?word=" + text)
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
//                Log.d("TEST : ", response.body().string());
                addText = response.body().string();
                Log.d("inProgress", "inin");
            }
        });
    }
}
