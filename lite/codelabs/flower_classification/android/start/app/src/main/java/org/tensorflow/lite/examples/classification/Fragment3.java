package org.tensorflow.lite.examples.classification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Fragment3 extends Fragment {
    private View view;
    private Button scrapButton;

    static public ArrayList<SearchList> scarpList;
    static public ArrayList<SearchList> copyList;

    private ListView listView;
    private ListViewAdapter listViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_3, container, false);
//        count = 1;
        final Context context = view.getContext();
        scarpList = new ArrayList<>();
        scarpList.add(new SearchList("water", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        //프래그먼트에 리스트뷰 추가
        listView = (ListView) view.findViewById(R.id.scrapView);
        listViewAdapter = new ListViewAdapter();
        listViewAdapter.setListViewItemList();
//        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.scrapView);
//        recyclerView.setHasFixedSize(true);
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);


        scrapButton = (Button) view.findViewById(R.id.scrapButton);
        scrapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scarpList.size() == 0) {
                    Toast.makeText(context, "비어있습니다.", Toast.LENGTH_SHORT).show();
                } else {
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
            }
        });

        return view;
    }
}