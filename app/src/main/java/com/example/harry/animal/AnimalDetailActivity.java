package com.example.harry.animal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.harry.animal.MyFragment.AnimalDetailFragment;
import com.example.harry.animal.MyModel.DataModel.StrayDogModel;

public class AnimalDetailActivity extends AppCompatActivity {

    MyFragmentHelper mMyFragmentHelper;


    private StrayDogModel mStrayDogModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);

        prepareData();

        mMyFragmentHelper = new MyFragmentHelper(this);
        mMyFragmentHelper.showFragment(R.id.fragment_detail_content, AnimalDetailFragment.getInstance() , GlobalVar.FRAGMENT_TAG_DETAIL_PAGE);
    }

    @Nullable
    @Override
    public ActionBar getSupportActionBar() {
        return null;
    }

    private void prepareData(){
        if(getIntent() != null && getIntent().getExtras() != null){
            Object obj = getIntent().getExtras().getSerializable(GlobalVar.BUNDLE_DETAIL_DATA_TAG);
            if(obj != null && obj instanceof StrayDogModel){
                mStrayDogModel = (StrayDogModel)getIntent().getExtras().getSerializable(GlobalVar.BUNDLE_DETAIL_DATA_TAG);
            }
        }
    }

    public StrayDogModel getStrayDogModel(){
        return mStrayDogModel;
    }
}
