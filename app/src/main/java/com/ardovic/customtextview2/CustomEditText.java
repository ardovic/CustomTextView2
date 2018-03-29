package com.ardovic.customtextview2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomEditText extends AppCompatEditText {

    Drawable mImage;


    public CustomEditText(Context context) {
        super(context);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_black_24dp, null);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    showClearButton();
                } else {
                    hideClearButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Use the getCompoundDrawables()[2] expression to check
                // if the drawable is on the "end" of text [2].
                if ((getCompoundDrawablesRelative()[2] != null)) {
                    float clearButtonStart; // Used for LTR languages
                    boolean isClearButtonClicked = false;

                    clearButtonStart = (getWidth() - getPaddingEnd()
                            - mImage.getIntrinsicWidth());

                    // If the touch occurred after the start of the button,
                    // set isClearButtonClicked to true.
                    if (event.getX() > clearButtonStart) {
                        isClearButtonClicked = true;
                    }

                    // Check for actions if the button is tapped.
                    if (isClearButtonClicked) {
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            getText().clear();
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
                return false;
            }
        });

    }

    void showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mImage,null);
    }

    void hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null,null);
    }


}
