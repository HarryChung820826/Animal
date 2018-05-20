package com.example.harry.animal.MyModel.DataModel;

import java.io.Serializable;

/**
 * Created by Harry on 2018/5/1.
 */

public class StrayDogModel implements Serializable{
    public long animal_id;         //動物的流水編號

    public String animal_subid;      //動物的區域編號
    public int animal_area_pkid;   //動物所屬縣市代碼((請對照縣市代碼表))
    public int animal_shelter_pkid;//動物所屬收容所代碼
    public String animal_place;    //動物的實際所在地
    public String animal_kind;     //動物的類型 [貓 | 狗 | 鳥 ..]
    public String animal_sex;      //動物性別[M | F | N](公、母、未輸入)
    public String animal_bodytype; //動物體型[MINI | SMALL | MEDIUM | BIG](迷你、小 型、中型、大型)
    public String animal_colour;   //動物毛色[黑色 | 灰色 | 白色.. ]
    public String animal_age;      //動物年紀[CHILD | ADULT](幼年、成年)
    public String animal_sterilization; //是否絕育[T | F | N](是、否、未輸入)
    public String animal_bacterin;      //是否施打狂犬病疫苗[T | F | N](是、否、未輸入)
    public String animal_foundplace;   //動物尋獲地
    public String animal_title;        //動物網頁標題
    public String animal_status;       //動物狀態[NONE | OPEN | ADOPTED | OTHER | DEAD](未公告、開放認養、已認養、其他、死亡)
    public String animal_remark;       //資料備註
    public String animal_caption;      //其他說明(此欄位資料僅供後臺使用人員參考、紀錄，資訊不提供給一般民眾)
    public String animal_opendate;       //開放認養時間(起)
    public String animal_closeddate;     //開放認養時間(迄)
    public String animal_update;         //動物資料異動時間
    public String animal_createtime;     //動物資料建立時間
    public String shelter_name;        //動物所屬收容所名稱
    public String album_name;          //圖片名稱(原始名稱)
    public String album_file;          //圖片名稱
    public String album_base64;        //圖片 base64 編碼
    public String album_update;          //異動時間
    public String cDate;                 //資料更新時間
    public String shelter_address;     //地址
    public String shelter_tel;         //連絡電話

    public StrayDogModel(){}
}
