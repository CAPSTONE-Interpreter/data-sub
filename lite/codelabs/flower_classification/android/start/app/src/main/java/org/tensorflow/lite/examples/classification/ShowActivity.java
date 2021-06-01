package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static org.tensorflow.lite.examples.classification.CameraActivity.addText;
import static org.tensorflow.lite.examples.classification.TextSearch.urls;

public class ShowActivity extends AppCompatActivity {

    private VideoView videoView;
    private ImageView like;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        videoView = findViewById(R.id.screenVideoView);
        like = findViewById(R.id.like);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String id = intent.getStringExtra("id");


        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(flag);
                if (flag) {
                    like.setImageResource(R.drawable.unlike);
                    Log.d("id", id);
                    deleteLike(id);
                } else {
                    like.setImageResource(R.drawable.like);
                    Log.d("id", id);
                    sendLike(id);
                }
                flag = !flag;
            }
        });

        Uri videoUri = Uri.parse(url);

//      컨트롤바 붙여주는 작업
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(videoUri);

//      비디오를 로딩 시간이 필요하므로 준비가 끝나면 실행하도록 리스너 설정
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });
    }

    //화면에 안보일때...
    @Override
    protected void onPause() {
        super.onPause();

        //비디오 일시 정지
        if (videoView != null && videoView.isPlaying()) videoView.pause();
    }

    //액티비티가 메모리에서 사라질때..
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //
        if (videoView != null) videoView.stopPlayback();
    }

    public static void sendLike(String id) {
        RequestBody requestBody = new FormBody.Builder()
                .build();


        Request request = new Request.Builder()
                .url("http://ec2-3-36-221-249.ap-northeast-2.compute.amazonaws.com:8080/scrap/igoman2@naver.com/" + id)
                .put(requestBody)
                .build();

        Log.d("asd", request.toString());

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
                Log.d("asdsadassa", response.body().string());
            }
        });
    }

    public static void deleteLike(String id) {
        RequestBody requestBody = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url("http://ec2-3-36-221-249.ap-northeast-2.compute.amazonaws.com:8080/scrap/igoman2@naver.com/" + id)
                .delete(requestBody)
                .build();

        Log.d("asd", request.toString());
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
                Log.d("asdsadassa", response.body().string());
            }
        });
    }

    public static void getLike() {
        Request request = new Request.Builder()
                .url("http://ec2-3-36-221-249.ap-northeast-2.compute.amazonaws.com:8080/scrap?email=igoman2@naver.com")
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            //          Callback function to check data returned
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("getLike", response.body().string());
            }
        });
    }


}
