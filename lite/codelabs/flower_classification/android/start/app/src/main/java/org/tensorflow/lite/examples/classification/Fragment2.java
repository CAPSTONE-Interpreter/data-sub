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

public class Fragment2 extends Fragment {
    private Button setList;
    private View view;
    static public ArrayList<SearchList> scarpList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_2, container, false);
        final Context context = view.getContext();

        setList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "비어있습니다.", Toast.LENGTH_SHORT).show();
                scarpList.add(new SearchList("water", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
            }
        });

        return view;
    }
}