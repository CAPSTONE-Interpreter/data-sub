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
import androidx.core.view.ViewCompat;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.relex.circleindicator.CircleIndicator3;

//import io.netty.handler.codec.http.multipart.FileUpload;

public class PhotoSearch extends AppCompatActivity {

    private ImageView imageViewSelected;
    private Button gallery, camera, btnImageSend;
    private File tempSelectFile;
    private Context context;
    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 5;
    private CircleIndicator3 mIndicator;
    static public ArrayList<URL> image_urls = new ArrayList<URL>(5);


    static public int indexNum = 0;
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
                                           setPager();
                                           FileUploadUtils.sendImage(new File("/sdcard/DCIM/Camera/test1.png"));

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

        mPager = findViewById(R.id.viewpager);
        //Adapter

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

    public void setPager(){
        imageViewSelected.setImageBitmap(null);
        pagerAdapter = new ListPageAdapter(this, num_page);

        mPager.setAdapter(pagerAdapter);

        //Indicator
        mIndicator = findViewById(R.id.indicator);


        //ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mPager.setCurrentItem(1000);
        mPager.setOffscreenPageLimit(3);

        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                indexNum = position % num_page;
                mIndicator.animatePageSelected(position % num_page);
            }

        });


        final float pageMargin = getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        final float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

        mPager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float myOffset = position * -(2 * pageOffset + pageMargin);
                if (mPager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(mPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.setTranslationX(-myOffset);
                    } else {
                        page.setTranslationX(myOffset);
                    }
                } else {
                    page.setTranslationY(myOffset);
                }
            }
        });
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
