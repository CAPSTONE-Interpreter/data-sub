package org.tensorflow.lite.examples.classification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static org.tensorflow.lite.examples.classification.TextSearch.urls;

public class Swipe5 extends Fragment {
    private TextView tvName5;

    public Swipe5() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipe5, container, false);
        tvName5 = view.findViewById(R.id.tvName5);
//        tvName5.setText(urls.get(4).text);
        return view;
    }
}