package com.ogi.dicodingacd.myviewmodel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyEditText extends AppCompatEditText {

    Drawable mClearButtonImage;

    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setHint("Masukan Nama Anda");
        setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
    }

    private void init()
    {
        mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_black, null);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if ((getCompoundDrawablesRelative()[2] != null)){
                    float clearButtonStart;
                    float clearButtonEnd;
                    boolean isClearButtonClicked = false;
                    if (getLayoutDirection() == LAYOUT_DIRECTION_RTL){
                        clearButtonEnd = mClearButtonImage.getIntrinsicWidth() + getPaddingStart();
                        if (event.getX() < clearButtonEnd){
                            isClearButtonClicked = true;
                        }else {
                            clearButtonStart = (getWidth() - getPaddingEnd() - mClearButtonImage.getIntrinsicWidth());
                            if (event.getX() > clearButtonStart){
                                isClearButtonClicked = true;
                            }
                        }
                    }
                    if (isClearButtonClicked){
                        if (event.getAction() == MotionEvent.ACTION_DOWN){
                            mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_black, null);
                            if (getText() != null){
                                getText().clear();
                            }
                            hideClearButton();
                            return true;
                        }else {
                            return false;
                        }
                    }
                    else {
                        return false;
                    }
                }
                return false;
            }
        });

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()){
                    showClearButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void hideClearButton()
    {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }

    private void showClearButton()
    {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButtonImage, null);
    }
}
