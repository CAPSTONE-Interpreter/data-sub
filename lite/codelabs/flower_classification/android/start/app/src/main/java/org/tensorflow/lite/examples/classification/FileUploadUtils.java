package org.tensorflow.lite.examples.classification;

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


                try {
                    JSONArray jsonArray = new JSONArray(strJsonOutput);
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

//                new Handler(getMainLooper()).post(new Runnable() {
//                    @Override
//                    public void run() {
//                        String jsonData = "{\"id\":329,\"title\":\"대나무\",\"url\":\"http://sldict.korean.go.kr/multimedia/multimedia_files/convert/20200820/732292/MOV000251453_700X466.webm\"},{\"id\":1384,\"title\":\"소나무,솔\",\"url\":\"http://sldict.korean.go.kr/multimedia/multimedia_files/convert/20160107/241975/MOV000254400_700X466.webm\"},{\"id\":1843,\"title\":\"나무,수목\",\"url\":\"http://sldict.korean.go.kr/multimedia/multimedia_files/convert/20191007/624819/MOV000248680_700X466.webm\"},{\"id\":3372,\"title\":\"꾸짖다,나무라다,야단치다,가책,구박,꾸중,꾸지람,책망,핍박,힐책\",\"url\":\"http://sldict.korean.go.kr/multimedia/multimedia_files/convert/20200821/733548/MOV000248396_700X466.webm\"}";
//                        try {
//                            JSONObject jsonObject = new JSONObject(jsonData);
//                            Log.d("asd", jsonObject.getString("title"));
//                            JSONArray Jarray = jsonObject.getJSONArray("title");
//                            Log.d("url", Jarray.getString(0));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });


//                final String strJsonOutput = response.body().string();
//                Log.d("TEST : ", strJsonOutput);
//                urls.add(new URL("소나무, 솔", "https://sldict.korean.go.kr/multimedia/multimedia_files/convert/20160107/241975/MOV000254400_700X466.webm"));
//                urls.add(new URL("나무, 수목", "https://sldict.korean.go.kr/multimedia/multimedia_files/convert/20191007/624819/MOV000248680_700X466.webm"));
//                Log.d("text : ", urls.get(0).text);
//                Log.d("url : ", urls.get(0).url);
//                Log.d("text : ", urls.get(1).text);
//                Log.d("url : ", urls.get(1).url);
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
