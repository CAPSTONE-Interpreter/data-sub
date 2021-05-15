package org.tensorflow.lite.examples.classification;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

//import io.netty.handler.codec.http.multipart.FileUpload;

public class PhotoSearch extends AppCompatActivity {

    private ImageView imageViewSelected;
    private Button gallery, camera, btnImageSend;
    private File tempSelectFile;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_search);

        btnImageSend = findViewById(R.id.btnImageSend);
        btnImageSend.setEnabled(false);
        btnImageSend.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Log.d("태그","보내기");
                                           FileUploadUtils.sendImage(new File("/sdcard/DCIM/Camera/qqq.png"));
                                       }
                                   }
        );


        gallery = findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_CREATE_DOCUMENT);
                startActivityForResult(intent, 1);
            }
        });

        camera = findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 2);
            }
        });

        imageViewSelected = findViewById(R.id.imgViewSelected);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.d("ca", "권한 설정 완료");
            } else {
                Log.d("ca", "권한 설정 요청");
                ActivityCompat.requestPermissions(PhotoSearch.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
//        gogo = findViewById(R.id.gogo);
//        gogo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "로그인 되었습니다.", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(PhotoSearch.this, ShowActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    // 권한 요청
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("ca", "onRequestPermissionsResult");
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            Log.d("ca", "Permission: " + permissions[0] + "was " + grantResults[0]);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Log.d("갤러리", "성공");
            Uri dataUri = data.getData();
            imageViewSelected.setImageURI(dataUri);
            System.out.println(dataUri);

            try {
                InputStream in = getContentResolver().openInputStream(dataUri);
                Bitmap image = BitmapFactory.decodeStream(in);
                in.close();

                String date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
                tempSelectFile = new File(Environment.getExternalStorageDirectory()+"/Pictures/Test/", "temp_" +date +".jpeg");
                OutputStream out = new FileOutputStream(tempSelectFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, out);}
            catch (IOException ex){
                ex.printStackTrace();
            }


            Log.d("갤러리", "경로");
            System.out.println(tempSelectFile);


            btnImageSend.setEnabled(true);

        } else if (requestCode == 2 && resultCode == Activity.RESULT_OK && data.hasExtra("data")) {
            Log.d("카메라", "성공");
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (bitmap != null) {
                imageViewSelected.setImageBitmap(bitmap);
            }
        }
    }

//    public String getRealPathFromURI(Uri contentUri) {
//
//        String[] proj = { MediaStore.Images.Media.DATA };
//
//        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
//        cursor.moveToNext();
//        String path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
//        Uri uri = Uri.fromFile(new File(path));
//
//        cursor.close();
//        return path;
//    }
}
