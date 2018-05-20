package com.example.harry.animal.TouchEventPra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.example.harry.animal.R;
import com.example.harry.animal.Tools.SystemTools;

public class TouchEventPraActivity extends AppCompatActivity implements View.OnTouchListener , View.OnClickListener{

    private final String TAG = "TouchEventPraActivity";
    private TouchEventPraView mTouchEventPraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event_pra);
        initView();
    }

    private void initView(){
        mTouchEventPraView = new TouchEventPraView(this);
        mTouchEventPraView.setOnClickListener(this);
        mTouchEventPraView.setOnTouchListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
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
        return super.dispatchTouchEvent(ev);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_txv:
                SystemTools.showLog("TouchEventMyTextView" , " onClick");
                SystemTools.showToast(TouchEventPraActivity.this , "my_txv OnCLick");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        String TAG = " None ";
        boolean moveIsCloseKeyBoard = false;
        switch (v.getId()) {
            case R.id.my_txv:
                TAG = "TouchEventMyTextView";
                break;
            case R.id.listview:
                moveIsCloseKeyBoard = true;
                TAG = "TouchEventListView";
                break;
            case R.id.touch_recyclerView:
                moveIsCloseKeyBoard = true;
                TAG = "TouchEventRecyclerView";
                break;
            default:
                break;
        }
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                SystemTools.showLog(TAG , "onTouch ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                SystemTools.showLog(TAG , "onTouch ACTION_MOVE");
                if(moveIsCloseKeyBoard){
                    SystemTools.closeKeyBoard(this , mTouchEventPraView.getMy_edtxv());
                }
                break;
            case MotionEvent.ACTION_UP:
                SystemTools.showLog(TAG , "onTouch ACTION_UP");
                if(!moveIsCloseKeyBoard){
                    SystemTools.closeKeyBoard(this , mTouchEventPraView.getMy_edtxv());
                }

                SystemTools.showLog("IsCheck" , mTouchEventPraView.isChecked() + "");
                if(mTouchEventPraView.isChecked()){
                    return true;
                }

                break;
            case MotionEvent.ACTION_CANCEL:
                SystemTools.showLog(TAG , "onTouch ACTION_CANCEL");
                break;
            default:
                break;
        }
        return false;
    }
}
