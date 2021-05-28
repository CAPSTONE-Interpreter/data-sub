package org.tensorflow.lite.examples.classification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static org.tensorflow.lite.examples.classification.TextSearch.urls;

public class Swipe3 extends Fragment {
    private TextView tvName3;

    public Swipe3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipe3, container, false);
        tvName3 = view.findViewById(R.id.tvName3);
        tvName3.setText(urls.get(2).text);
        return view;
    }
}