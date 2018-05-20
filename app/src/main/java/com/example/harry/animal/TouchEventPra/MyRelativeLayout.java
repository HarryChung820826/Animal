package com.example.harry.animal.TouchEventPra;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.example.harry.animal.Tools.SystemTools;

/**
 * Created by Harry on 2018/5/19.
 */

public class MyRelativeLayout extends RelativeLayout {

    private final String TAG = "TouchEventMyRelativeLayout";

    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                SystemTools.showLog(TAG , "onInterceptTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                SystemTools.showLog(TAG , "onInterceptTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                SystemTools.showLog(TAG , "onInterceptTouchEvent ACTION_UP");
                SystemTools.showToast(getContext(), "onInterceptTouchEvent UP");
//                return true;
                break;
            case MotionEvent.ACTION_CANCEL:
                SystemTools.showLog(TAG , "onInterceptTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
