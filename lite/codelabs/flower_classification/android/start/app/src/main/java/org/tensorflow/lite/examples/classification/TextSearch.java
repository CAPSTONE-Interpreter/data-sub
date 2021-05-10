package org.tensorflow.lite.examples.classification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TextSearch extends AppCompatActivity {

    private Button submitBtn;
    private ListView listView;
    private ListViewAdapter listViewAdapter;
    static public ArrayList<SearchList> scarpList;
    static public ArrayList<SearchList> copyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_search);


        scarpList = new ArrayList<>();
        scarpList.add(new SearchList("water", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        scarpList.add(new SearchList("tree", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));

        listView = findViewById(R.id.scrapView);
        listViewAdapter = new ListViewAdapter();
        listViewAdapter.setListViewItemList2();

        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FileUploadUtils.sendText("text");
//                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
//                startActivity(intent);
                copyList = new ArrayList<>(scarpList);
//                    final ListViewAdapter<Company> arrayAdapter = new ArrayAdapter<Company>(getActivity(), android.R.layout.simple_list_item_1, scarpList);
//                    CompanyAdapter adapter = new CompanyAdapter(context, copyList);
                listView.setAdapter(listViewAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                            Intent intent = new Intent(view.getContext(), MainActivity.class);
//                            startActivity(intent);
//                            AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        Intent intent = new Intent(view.getContext(), ShowActivity.class);
                        intent.putExtra("position", Integer.toString(position));
                        startActivity(intent);
                    }
                });
            }
        });
    }
}