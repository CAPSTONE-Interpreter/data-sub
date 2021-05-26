    package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;


import me.relex.circleindicator.CircleIndicator3;
import java.util.ArrayList;
import static org.tensorflow.lite.examples.classification.TextSearch.urls;

public class    TextSearch extends AppCompatActivity implements OnListItemClickListener {

    private Button submitBtn, indexBtn;
    private ListView listView;
    static public ArrayList<SearchList> scarpList;
    static public ArrayList<SearchList> copyList;
    private CircleIndicator3 mIndicator;
    private EditText textLine;

    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 5;

    static public int indexNum = 0;
    static public ArrayList<URL> urls = new ArrayList<URL>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_search);

        textLine = findViewById(R.id.textLine);

        scarpList = new ArrayList<>();
        scarpList.add(new SearchList("water", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        scarpList.add(new SearchList("tree", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));

        listView = findViewById(R.id.scrapView);

        submitBtn = findViewById(R.id.submitBtn);
        indexBtn = findViewById(R.id.index);

        //ViewPager2
        mPager = findViewById(R.id.viewpager);
        //Adapter
        pagerAdapter = new ListPageAdapter(this, num_page);

//        mPager.setAdapter(pagerAdapter);

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

        indexBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), Integer.toString(indexNum), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                intent.putExtra("url", urls.get(indexNum).url);
                startActivity(intent);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexBtn.setVisibility(View.VISIBLE);
                mPager.setAdapter(pagerAdapter);
                mIndicator.setViewPager(mPager);
                mIndicator.createIndicators(num_page, 0);

                String sendText = textLine.getText().toString();
                Log.d("calling", sendText);
                FileUploadUtils.sendText(sendText);


//                FileUploadUtils.sendText("text");
////                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
////                startActivity(intent);
//                copyList = new ArrayList<>(scarpList);
////                    final ListViewAdapter<Company> arrayAdapter = new ArrayAdapter<Company>(getActivity(), android.R.layout.simple_list_item_1, scarpList);
////                    CompanyAdapter adapter = new CompanyAdapter(context, copyList);
//                listView.setAdapter(listViewAdapter);
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
////                            Intent intent = new Intent(view.getContext(), MainActivity.class);
////                            startActivity(intent);
////                            AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                        Intent intent = new Intent(view.getContext(), ShowActivity.class);
//                        intent.putExtra("position", Integer.toString(position));
//                        startActivity(intent);
//                    }
//                });
            }
        });
    }

    @Override
    public void onItemClick(ListAdapter.ViewHolder holder, View view, int position) {

    }
}