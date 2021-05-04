package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment1 extends Fragment {

    private Button textBtn, photoBtn, motionBtn;

    public Fragment1() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_1,container,false);

//    text
        textBtn = view.findViewById(R.id.text);
        textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), TextSearch.class);
                startActivity(intent);
            }});
//photo
        photoBtn = view.findViewById(R.id.photo);
        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), PhotoSearch.class);
                startActivity(intent);
            }}
        );
//motion
        motionBtn = view.findViewById(R.id.motion);
        motionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), ClassifierActivity.class);
                startActivity(intent);
            }});


        // Inflate the layout for this fragment
        return view;
    }
}