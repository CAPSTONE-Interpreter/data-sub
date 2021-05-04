package org.tensorflow.lite.examples.classification;

import android.view.View;

//리스너 안에서 토스트 메시지를 띄우게 되면 클릭했을 때 기능이 변경될 때마다 어댑터를 수정해야 하는 문제가 생김.
//따라서 어댑터 객체 밖에서 리스너를 설정하고 설정된 리스너 쪽으로 이벤트를 전달받도록 하는 것이 좋음.
public interface OnListItemClickListener {
    public void onItemClick(ListAdapter.ViewHolder holder, View view, int position);
}
