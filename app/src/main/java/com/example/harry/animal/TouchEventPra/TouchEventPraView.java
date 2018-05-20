package com.example.harry.animal.TouchEventPra;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import com.example.harry.animal.R;
import com.example.harry.animal.Tools.SystemTools;

import java.util.ArrayList;

/**
 * Created by Harry on 2018/5/20.
 */

public class TouchEventPraView {

    private AppCompatActivity activity;
    private Switch switch_btn;
    private MyTextView my_txv;
    private MyEditTextView my_edtxv;
    private ListView mListView;
    private RecyclerView recyclerView;

    private boolean isCheck = false;

    public TouchEventPraView(AppCompatActivity activity) {
        this.activity = activity;
        init();
    }


    private void init(){
        switch_btn = ((Switch) activity.findViewById(R.id.switch_btn));
        my_txv = ((MyTextView) activity.findViewById(R.id.my_txv));
        my_edtxv = ((MyEditTextView) activity.findViewById(R.id.my_edtxv));
        mListView = ((ListView) activity.findViewById(R.id.listview));
        recyclerView = ((RecyclerView) activity.findViewById(R.id.touch_recyclerView));
        switch_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCheck = isChecked;
            }
        });
        initListView();
        initRecyclerView();
    }

    protected MyEditTextView getMy_edtxv(){
        return my_edtxv;
    }

    protected boolean isChecked() {
        return isCheck;
    }

    protected void setOnTouchListener(View.OnTouchListener onTouchListener){
        if(onTouchListener != null){
            my_txv.setOnTouchListener(onTouchListener);
            mListView.setOnTouchListener(onTouchListener);
//            recyclerView.setOnTouchListener(onTouchListener);
        }
    }

    protected void setOnClickListener(View.OnClickListener onClickListener){
        if(onClickListener != null){
            my_txv.setOnClickListener(onClickListener);
        }
    }


    private void initListView(){
        final ArrayList<String> data = new ArrayList<>();
        for(int i = 0 ; i < 30 ; i ++ ){
            data.add("TempData_"+(i+1));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SystemTools.showToast(activity,data.get(position));
            }
        });
    }

    private void initRecyclerView(){
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(new TouchRecyclerViewAdapter(activity));
        final String TAG = "RecyclerView";
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                switch(e.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        SystemTools.showLog(TAG , "onTouch ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        SystemTools.showLog(TAG , "onTouch ACTION_MOVE");
                        SystemTools.closeKeyBoard(activity , my_edtxv);
                        break;
                    case MotionEvent.ACTION_UP:
                        SystemTools.closeKeyBoard(activity , my_edtxv);
                        SystemTools.showLog("IsCheck" , isCheck + "");
                        if(isCheck){
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

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}
