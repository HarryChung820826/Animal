package com.example.harry.animal.RealmTools;

/**
 * Created by Harry on 2018/5/13.
 */

public class LoadManager {

    int pageSize = 10 ; //每一頁的筆數
    int CurrentPageIndex = 0 ; //標記目前是第幾頁

    public void LoadManager(){}

    public void LoadManager(int pageSize){
        this.pageSize = pageSize;
    }

    public int getCurrentPageIndex(){
        return CurrentPageIndex;
    }

    //取得目前 起始 index
    public int getCurrentStartIndex(){
        return CurrentPageIndex * pageSize + 1;
    }

    //取得目前 結束 index
    public int getCurrentEndIndex(){
        return (CurrentPageIndex + 1) * pageSize;
    }

    //設定下一頁
    public void NextPage(){
        CurrentPageIndex += 1;
    }
}
