package com.example.harry.animal.RealmTools;

import com.example.harry.animal.MyModel.DataModel.StrayDogModel;
import com.google.gson.Gson;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Harry on 2017/5/29.
 */
public class AnimalRealmObject extends RealmObject{
    @PrimaryKey
    public long animal_id;         //動物的流水編號

    public String animal_place;    //動物的實際所在地
    public String animal_kind;     //動物的類型 [貓 | 狗 | 鳥 ..]
    public String animal_opendate;       //開放認養時間(起)
    public String cDate;                 //資料更新時間
    public String AllJsonString;    //儲存完整json 字串

    public StrayDogModel ConvertToAnimalObj(){
        return new Gson().fromJson(this.AllJsonString,StrayDogModel.class);
    }

}
