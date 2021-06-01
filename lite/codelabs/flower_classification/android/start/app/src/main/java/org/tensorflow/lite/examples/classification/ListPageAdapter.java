package org.tensorflow.lite.examples.classification;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import static org.tensorflow.lite.examples.classification.TextSearch.indexNum;




public class ListPageAdapter extends FragmentStateAdapter {

    public int mCount;
    private LayoutInflater mInflate;
    private OnListItemClickListener listener;

    public ListPageAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(index==0) {
//            indexNum = index;
            return new Swipe1();
        }
        else if(index==1){
//            indexNum = index;
            return new Swipe2();
        }
        else if(index==2) {
//            indexNum = 2;
            return new Swipe3();
        }
        else if(index==3) {
//            indexNum = index;
            return new Swipe4();
        }
        else {
//            indexNum = index;
            return new Swipe5();
        }

    }

    @Override
    public int getItemCount() {
        return 2000;
    }

    public int getRealPosition(int position) { return position % mCount; }


//    @Override
//    public void onItemClick(ListAdapter.ViewHolder holder, View view, int position) {
//        if(listener != null)
//            listener.onItemClick(holder, view ,position);
//    }
//
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public ViewHolder(View itemView, OnListItemClickListener listener) {
//            super(itemView);
//
//            itemView.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view){
//                    int position = getAdapterPosition();
//                    AppCompatActivity activity = (AppCompatActivity)view.getContext();
//                    Log.d("pager", Integer.toString(position));
//                }
//            });
//        }
//    }
}