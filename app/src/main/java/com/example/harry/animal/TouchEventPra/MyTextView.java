package com.example.harry.animal.TouchEventPra;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.harry.animal.Tools.SystemTools;

/**
 * Created by Harry on 2018/5/19.
 * View - dispatchTouchEvent
 */

@SuppressLint("AppCompatCustomView")
public class MyTextView extends TextView {

    private final String TAG = "TouchEventMyTextView";

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                SystemTools.showLog(TAG , "dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                SystemTools.showLog(TAG , "dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                SystemTools.showLog(TAG , "dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                SystemTools.showLog(TAG , "dispatchTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                SystemTools.showLog(TAG , "onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                SystemTools.showLog(TAG , "onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                SystemTools.showLog(TAG , "onTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                SystemTools.showLog(TAG , "onTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
