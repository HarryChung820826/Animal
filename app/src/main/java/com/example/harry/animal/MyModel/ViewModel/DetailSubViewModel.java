package com.example.harry.animal.MyModel.ViewModel;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.harry.animal.MyModel.DataModel.StrayDogModel;
import com.example.harry.animal.R;
import com.example.harry.animal.Tools.SystemTools;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Harry on 2018/5/6.
 */

public class DetailSubViewModel {

    private FragmentActivity mFragmentActivity;
    private LinearLayout dragView;
    private CircleImageView item_img_layout;
    private RecyclerView mRecyclerView;

    private StrayDogModel mStrayDogModel;
    private float coefficient = 5; //圓形圖放大倍數
    private float MaxSize = 0 ; //最大Size 限制
    private float newSize = 0 ; //計算後的大小
    private float defaultSize = 0;

    public DetailSubViewModel(FragmentActivity mFragmentActivity , View view){
        this.mFragmentActivity = mFragmentActivity;
        defaultSize = SystemTools.convertDpToPixel(70 , mFragmentActivity);
        if(view != null)
            init(view);
    }

    private void init(View view){
        dragView = ((LinearLayout) view.findViewById(R.id.dragView));
        item_img_layout = ((CircleImageView) view.findViewById(R.id.item_img_layout));
        mRecyclerView = (RecyclerView) view.findViewById(R.id.detail_recycler_view);
        MaxSize = defaultSize * coefficient;
    }

    public DetailSubViewModel setStrayDogModel(StrayDogModel mStrayDogModel){
        this.mStrayDogModel = mStrayDogModel;
        return this;
    }

    public void build(){
        if(mStrayDogModel != null){
            if(mStrayDogModel.album_file!=null && item_img_layout!= null) {
                Glide.with(mFragmentActivity)
                        .load(mStrayDogModel.album_file)
                        .into(item_img_layout);
            }
        }
    }

    public void transferCircleView(float slideOffset){
        Log.d("transferCircleView",item_img_layout.getHeight()+" , "+ item_img_layout.getHeight() * (1+slideOffset) +" , "+MaxSize);
        if(item_img_layout.getHeight() * (1+slideOffset) <= MaxSize){
            newSize = defaultSize * (1+slideOffset);
            item_img_layout.post(new Runnable() {
                @Override
                public void run() {
                    android.view.ViewGroup.LayoutParams params = item_img_layout
                            .getLayoutParams();
                    params.width = (int)newSize;
                    params.height = (int)newSize;
                    item_img_layout.setLayoutParams(params);
                }
            });
        }
    }

//    public static class DetailSubViewVarDataModel{
//        public StrayDogModel mStrayDogModel;
//
//        public DetailSubViewVarDataModel(StrayDogModel mStrayDogModel) {
//            this.mStrayDogModel = mStrayDogModel;
//        }
//    }
}
