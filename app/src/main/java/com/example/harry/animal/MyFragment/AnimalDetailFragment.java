package com.example.harry.animal.MyFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.harry.animal.AnimalDetailActivity;
import com.example.harry.animal.MyModel.DataModel.StrayDogModel;
import com.example.harry.animal.MyModel.ViewModel.DetailSubViewModel;
import com.example.harry.animal.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

/**
 * Created by Harry on 2018/5/6.
 */

public class AnimalDetailFragment extends Fragment {

    private static AnimalDetailFragment mAnimalDetailFragment;
    private SlidingUpPanelLayout sliding_layout;
    private ImageView bigPic;
    private LinearLayout dragView;
    private DetailSubViewModel mDetailSubViewModel;

    public static AnimalDetailFragment getInstance(){
        if(mAnimalDetailFragment == null){
            mAnimalDetailFragment = new AnimalDetailFragment();
        }
        return mAnimalDetailFragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animal_detail_layout, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        sliding_layout = ((SlidingUpPanelLayout) view.findViewById(R.id.sliding_layout));
        initSlidingUpPanelLayout(sliding_layout);
        bigPic = ((ImageView) view.findViewById(R.id.bigPic));
        dragView =  ((LinearLayout) view.findViewById(R.id.dragView));
        mDetailSubViewModel = new DetailSubViewModel(getActivity() , dragView);
        initData();
    }

    /**
     * 準備資料
     * from realm or internet*/
    private void initData(){
        if(getActivity() instanceof AnimalDetailActivity && mDetailSubViewModel != null){
            StrayDogModel mStrayDogModel = ((AnimalDetailActivity)getActivity()).getStrayDogModel();
            Glide.with(getActivity())
                    .load(mStrayDogModel.album_file)
                    .into(bigPic);
            mDetailSubViewModel.setStrayDogModel(mStrayDogModel).build();
        }
    }

    private void initSlidingUpPanelLayout(SlidingUpPanelLayout sliding_layout){
        sliding_layout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.d(" SlidingUpPanel ", slideOffset+"");
                mDetailSubViewModel.transferCircleView(slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

            }
        });
    }



}
