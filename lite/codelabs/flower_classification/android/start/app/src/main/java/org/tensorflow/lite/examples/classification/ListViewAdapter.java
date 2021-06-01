package org.tensorflow.lite.examples.classification;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static org.tensorflow.lite.examples.classification.Fragment3.scarpList;


public class ListViewAdapter extends BaseAdapter {

private Button delete;
private android.widget.TextView similarity;
public ArrayList<SearchList> listViewItemSearchList = new ArrayList<SearchList>();
public String[] listViewItemList2;
private ImageView rankImage;
private Context thisContext;
int number = 0;

@Override
public int getCount(){
    return listViewItemSearchList.size();
}

@Override
public Object getItem(int position){
    return listViewItemSearchList.get(position);
}

@Override
public long getItemId(int position){
    return position;
}

@Override
public View getView(final int position, android.view.View convertView, ViewGroup parent){
    final int pos = position;
    final Context context = parent.getContext();
    thisContext = context;

    if(number == 0) {
        System.out.println("1");
        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        delete = (Button) convertView.findViewById(R.id.deleteButton);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewItemSearchList.remove(position);
//                scarpSearchList.remove(position);
                notifyDataSetChanged();
            }
        });
        System.out.println("2");

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        android.widget.TextView textView = (android.widget.TextView) convertView.findViewById(R.id.textView);
        android.widget.TextView textView2 = (android.widget.TextView) convertView.findViewById(R.id.textView2);
        // Data Set(filteredItemList)에서 position에 위치한 데이터 참조 획득
        SearchList searchListViewItem = listViewItemSearchList.get(position);

        System.out.println("3");

        textView.setText(searchListViewItem.getText());
//        textView2.setText(searchListViewItem.getUrl());
    }
    else{ // 유사 자소서
        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item2, parent, false);
        }
        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        android.widget.TextView textView = (android.widget.TextView) convertView.findViewById(R.id.textView);
        // Data Set(filteredItemList)에서 position에 위치한 데이터 참조 획득
        SearchList searchListViewItem = listViewItemSearchList.get(position);
        textView.setText(searchListViewItem.getText());
    }
    return convertView;
}

public void setListViewItemList(){
    listViewItemSearchList = new ArrayList<>(scarpList);
    number = 1;
}

public void setListViewItemList2(){
    listViewItemSearchList = new ArrayList<>(scarpList);
    number = 1;
}

public void clearItem(){
    listViewItemSearchList.clear();
}
}
