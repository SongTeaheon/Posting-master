package com.songtaeheon.posting.Utils;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

//grid view 내부 이미지를 정사각형으로 만들기 위해 커스텀한 이미지 뷰 클래스
public class MyGridImageView extends AppCompatImageView {

    public MyGridImageView(Context context) {
        super(context);
    }

    public MyGridImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }
}
