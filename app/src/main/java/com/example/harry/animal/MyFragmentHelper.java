package com.example.harry.animal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Stack;

/**
 * Created by Harry on 2018/5/1.
 */

public class MyFragmentHelper {

    private FragmentActivity activity;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Stack<Fragment> fragmentStack = new Stack<Fragment>();

    MyFragmentHelper(FragmentActivity activity){
        this.activity = activity;
        if(this.activity != null){
            mFragmentManager = activity.getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
        }
    }

    public FragmentManager getmFragmentManager() {
        return mFragmentManager;
    }

    public void showFragment(int addToWhichViewId,Fragment mFragment , String tag){
        if(canOperating()){
            this.mFragmentTransaction.add(addToWhichViewId , mFragment , tag);
            this.mFragmentTransaction.commit();
        }
    }

    public void  removeFragment(){
        if(canOperating()){
//            this.mFragmentTransaction.remove()
        }
    }


    private boolean canOperating(){
        if(mFragmentManager != null && mFragmentTransaction != null)
            return true;
        return false;
    }

}
