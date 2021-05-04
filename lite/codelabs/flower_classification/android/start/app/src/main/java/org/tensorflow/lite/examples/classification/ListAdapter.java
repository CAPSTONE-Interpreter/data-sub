package org.tensorflow.lite.examples.classification;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filterable;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements Filterable, OnListItemClickListener {
    public static ArrayList<SearchList> result;
    private java.util.List<SearchList> mList;
    private java.util.List<SearchList> mListFull;
    private LayoutInflater mInflate;
    private static Context mContext;
    private OnListItemClickListener listener;
    private Button deleteButton;

    public ListAdapter(Context context, ArrayList<SearchList> lists){
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
        this.mList = lists;
        //독립적으로 관리하기 위해 생성자를 통해 복사본 초기화 (mCompanyFull = mCompany로 초기화하면 값이 같이 바뀜)
        mListFull = new ArrayList<>(mList);
        result = new ArrayList<>();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) { //뷰 홀더를 생성하고 뷰를 붙여주는 부분
        android.view.View view = mInflate.inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view, (OnListItemClickListener) this);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) { //재활용 되는 뷰가 호출하여 실행되는 메소드, position의 데이터를 뷰홀더의 아이템뷰에 표시
        //데이터로 뷰를 바인딩
        viewHolder.textView.setText(mList.get(position).text);
        viewHolder.textView2.setText(mList.get(position).url);
    }

    //데이터 개수 리턴
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public SearchList getItem(int position){
        return mList.get(position);
    }

    public java.util.List<SearchList> getmCompany(){
        return mList;
    }

    static public ArrayList<SearchList> getResult() {
        return result;
    }

    //데이터 위치 리턴

    //외부에서 리스너를 설정할 수 있도록 메소드 추가
    public void setOnItemClickListener(OnListItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, android.view.View view, int position) {
        if(listener != null)
            listener.onItemClick(holder, view, position);
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        android.widget.TextView textView;
        android.widget.TextView textView2;
        ArrayList<SearchList> arrayList = new ArrayList<>();

        // 뷰 객체에 대한 참조
        public ViewHolder(android.view.View itemView, final OnListItemClickListener listener) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            deleteButton.setVisibility(itemView.INVISIBLE);

            //아이템 뷰에 클릭 리스터 설정
            itemView.setOnClickListener(new android.view.View.OnClickListener() {
                                            @Override
                                            public void onClick(android.view.View view) {
                                                int position = getAdapterPosition(); //선택된 아이템의 위치를 얻음
                                                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                                                Intent intent = new Intent(activity, ShowActivity.class);
                                                intent.putExtra("position", Integer.toString(position));
                                                activity.startActivity(intent);
                }
            });
        }
    }

    @Override
    public android.widget.Filter getFilter(){
        return exampleFilter;
    }

    private android.widget.Filter exampleFilter = new android.widget.Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            java.util.List<SearchList> filteredlist = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredlist.addAll(mListFull);
            }
            else{
                String filteredPattern = constraint.toString().toLowerCase().trim();
                for(SearchList list : mListFull){
                    if(list.getText().toLowerCase().contains(filteredPattern)){
                        filteredlist.add(list);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mList.clear();
            mList.addAll((java.util.List)results.values);
            notifyDataSetChanged();
        }
    };
}
