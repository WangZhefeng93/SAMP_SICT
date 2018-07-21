package com.ff161224.cc_commander.shareplatform;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by xiaobaixiaoda on 2018/4/5.
 * 该类集成网格布局，形成自定义的网格布局控件
 */

public class SearchGridView extends GridView {
    public SearchGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public SearchGridView(Context context) {
        super(context);
    }
    public SearchGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
