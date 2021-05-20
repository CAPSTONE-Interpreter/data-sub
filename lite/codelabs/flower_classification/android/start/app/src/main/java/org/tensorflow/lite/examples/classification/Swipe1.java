package org.tensorflow.lite.examples.classification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class Swipe1 extends Fragment {
    private ImageButton imageButton1;
    private Button button;

    public Swipe1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_swipe1,container,false);

//        button = view.findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Log.d("zz","qqqq");
//            }
//        });

        imageButton1 = view.findViewById(R.id.imageButton1);
        imageButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("asd","zzzz");
            }
        });


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swipe1, container, false);




    }
}