package org.tensorflow.lite.examples.classification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static org.tensorflow.lite.examples.classification.TextSearch.urls;

public class Swipe4 extends Fragment {
    private TextView tvName4;

    public Swipe4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipe4, container, false);
        tvName4 = view.findViewById(R.id.tvName4);
        tvName4.setText(urls.get(3).text);
        return view;
    }
}