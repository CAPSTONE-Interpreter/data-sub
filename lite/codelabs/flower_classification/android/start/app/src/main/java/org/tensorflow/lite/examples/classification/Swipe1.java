package org.tensorflow.lite.examples.classification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static org.tensorflow.lite.examples.classification.TextSearch.urls;

public class Swipe1 extends Fragment implements View.OnClickListener {
    private TextView tvName1;

    public Swipe1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipe1, container, false);
        tvName1 = (TextView)view.findViewById(R.id.tvName1);
        tvName1.setText(urls.get(0).text);


        return view;
    }

//    @Override
//    public void onResume() {
//        tvName1.setText(urls.get(0).text);
//        super.onResume();
//    }
    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(), "asdasczxczx", Toast.LENGTH_SHORT).show();
    }
}