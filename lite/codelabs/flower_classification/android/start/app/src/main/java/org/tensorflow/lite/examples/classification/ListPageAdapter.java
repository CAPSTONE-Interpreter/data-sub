package org.tensorflow.lite.examples.classification;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import static org.tensorflow.lite.examples.classification.TextSearch.scarpList;

//import static org.tensorflow.lite.examples.classification.Fragment3.scarpList;


public class ListPageAdapter extends FragmentStateAdapter {

    public int mCount;

    public ListPageAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(index==0) return new Swipe1();
        else if(index==1) return new Swipe2();
        else if(index==2) return new Swipe3();
        else if(index==3) return new Swipe4();
        else return new Swipe5();

    }

    @Override
    public int getItemCount() {
        return 2000;
    }

    public int getRealPosition(int position) { return position % mCount; }


}