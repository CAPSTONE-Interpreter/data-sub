package org.tensorflow.lite.examples.classification;

import android.content.Context;
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



import org.jetbrains.annotations.NotNull;

public class Swipe1 extends Fragment implements View.OnClickListener {
    private TextView tvName1;
    private ImageButton imageButton1;
    private Button button;

    public Swipe1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_swipe1,container,false);
        RelativeLayout l = v.findViewById(R.id.rl);
        l.setOnClickListener(this);
        //        final View view = inflater.inflate(R.layout.fragment_swipe1,container,false);


//        button = view.findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Log.d("zz","qqqq");
//            }
//        });



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swipe1, container, false);




    }


    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(), "asdasczxczx", Toast.LENGTH_SHORT).show();
    }
}