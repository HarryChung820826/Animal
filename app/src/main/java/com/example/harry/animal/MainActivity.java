package com.example.harry.animal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.harry.animal.MyFragment.StrayDogFragment;
import com.example.harry.animal.MyModel.ViewModel.MainFunItemViewModel;

import java.util.ArrayList;

public class MainActivity extends Navigation_BaseActivity {

    MyFragmentHelper mMyFragmentHelper;
    private LinearLayout main_top_layout;
    private FloatingActionButton fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyFragmentHelper = new MyFragmentHelper(this);
        mMyFragmentHelper.showFragment(R.id.fragment_content,StrayDogFragment.getInstance(),GlobalVar.FRAGMENT_TAG_MAIN);
        init();
        //test
    }


    private void init(){
        
        //use self custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpToolBar();//使用父類別的setUpToolBar()，設置ToolBar
        CurrentMenuItem = 0;//目前Navigation項目位置
        NV.getMenu().getItem(CurrentMenuItem).setChecked(true);//設置Navigation目前項目被選取狀態

        initFunOptionLayout();
        initView();
    }

    private void initView(){
        fabButton = (FloatingActionButton)this.findViewById(R.id.fabButton);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMyFragmentHelper!=null && mMyFragmentHelper.getmFragmentManager() != null){
                    if(mMyFragmentHelper.getmFragmentManager().findFragmentByTag(GlobalVar.FRAGMENT_TAG_MAIN) instanceof onFloatListener){
                        ((onFloatListener)mMyFragmentHelper.getmFragmentManager().findFragmentByTag(GlobalVar.FRAGMENT_TAG_MAIN)).onFloatClick(v);
                    }
                }
            }
        });
    }

    private void initFunOptionLayout(){
        main_top_layout = ((LinearLayout) findViewById(R.id.main_top_layout));
        ArrayList<MainFunItemViewModel.MainFunItemViewVarDataModel> dataModels = new ArrayList<>();
        dataModels.add(new MainFunItemViewModel.MainFunItemViewVarDataModel(
                this.getResources().getString(R.string.fun_option_animal),
                R.drawable.pets_dog,
                null));
        dataModels.add(new MainFunItemViewModel.MainFunItemViewVarDataModel(
                this.getResources().getString(R.string.fun_option_discussion_board),
                R.drawable.pets_dog,
                null));
        dataModels.add(new MainFunItemViewModel.MainFunItemViewVarDataModel(
                this.getResources().getString(R.string.fun_option_discussion_board),
                R.drawable.pets_dog,
                null));
        //建立View 並 addView 到UI上
        for(MainFunItemViewModel.MainFunItemViewVarDataModel mMainFunItemViewVarDataModel : dataModels){
            main_top_layout.addView(new MainFunItemViewModel(this , main_top_layout)
                    .setFunName(mMainFunItemViewVarDataModel.funName)
                    .setFunImg(mMainFunItemViewVarDataModel.FunImgURL)
                    .setFunImgID(mMainFunItemViewVarDataModel.picID)
                    .setFunClickListener(mMainFunItemViewVarDataModel.onFunClickListener).build());
        }
    }

    public interface onFloatListener{
        public void onFloatClick(View view);
    }
}
