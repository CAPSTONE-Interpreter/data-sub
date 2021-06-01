package org.tensorflow.lite.examples.classification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.tensorflow.lite.examples.classification.TextSearch.urls;

public class Fragment3 extends Fragment {
    private View view;
    private Button getScrap;

    static public ArrayList<SearchList> scarpList;
    static public ArrayList<SearchList> copyList;

    private ListView listView;
    private ListViewAdapter listViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_3, container, false);
        final Context context = view.getContext();
        scarpList = new ArrayList<>();

        getLike();
//        getScrap =view.findViewById(R.id.getScrap);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                //프래그먼트에 리스트뷰 추가
                listView = (ListView) view.findViewById(R.id.scrapView);
                if (scarpList.size() == 0) {
                    Toast.makeText(context, "비어있습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    copyList = new ArrayList<>(scarpList);
                    listViewAdapter = new ListViewAdapter();
                    listViewAdapter.setListViewItemList();
                    listView.setAdapter(listViewAdapter);
                }
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Intent intent = new Intent(view.getContext(), ShowActivity.class);
                        intent.putExtra("url", scarpList.get(position).url);
                        intent.putExtra("id", scarpList.get(position).id);
                        startActivity(intent);
                    }
                });
            }
        }, 200);

        return view;
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
                final String strJsonOutput = response.body().string();
                try {
                    JSONArray jsonArray = new JSONArray(strJsonOutput);
                    Log.d("TEST : ", strJsonOutput);

                    for(int i=0; i<jsonArray.length(); i++){
                        scarpList.add(new SearchList(jsonArray.getJSONObject(i).getString("id"), jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("url")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}