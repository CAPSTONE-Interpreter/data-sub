package org.tensorflow.lite.examples.classification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static org.tensorflow.lite.examples.classification.Fragment3.scarpList;
import androidx.fragment.app.Fragment;

public class Fragment4 extends Fragment {
    private TextView scrapNumber;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\
        view = inflater.inflate(R.layout.fragment_4, container, false);

        scrapNumber = view.findViewById(R.id.scrapNumber);
        scrapNumber.setText(Integer.toString(scarpList.size()));

        return view;
    }
}