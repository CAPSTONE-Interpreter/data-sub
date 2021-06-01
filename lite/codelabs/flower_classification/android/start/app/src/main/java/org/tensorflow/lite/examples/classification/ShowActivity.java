package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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

        if(flag){
            like.setImageResource(R.drawable.unlike);
        }
        else{
            like.setImageResource(R.drawable.like);
        }

        like.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                flag = !flag;
                System.out.println(flag);

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
        if(videoView!=null && videoView.isPlaying()) videoView.pause();
    }
    //액티비티가 메모리에서 사라질때..
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //
        if(videoView!=null) videoView.stopPlayback();
    }

}
