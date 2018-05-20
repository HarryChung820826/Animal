package com.example.harry.animal.MyModel.DataModel;

import android.content.Context;
import android.os.AsyncTask;

import com.example.harry.animal.RealmTools.AnimalRealm;
import com.example.harry.animal.RealmTools.AnimalRealmObject;
import com.example.harry.animal.Tools.SystemTools;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.harry.animal.RealmTools.RealmConfig.AnimalRealmName;

/**
 * Created by Harry on 2018/5/13.
 */

public class StrayAnimalModelFactory {

    private Context mContext;
    PrepareCallBack prepareCallBack ;
    ParseJsonDataAsync mParseJsonDataAsync ;

    public StrayAnimalModelFactory(Context mContext){
        this.mContext = mContext;
    }

    public void parseJsonData(String jsonStr , PrepareCallBack prepareCallBack){
        try {
            this.prepareCallBack = prepareCallBack;
            if(mParseJsonDataAsync!=null) {
                mParseJsonDataAsync.cancel(true);
            }else{
                mParseJsonDataAsync = new ParseJsonDataAsync();
            }
            mParseJsonDataAsync.execute(jsonStr);
        }catch(Exception e){}
    }

    /** Parse from internet's data */
    private class ParseJsonDataAsync extends AsyncTask<String , Void , ArrayList<StrayDogModel>>{
        @Override
        protected ArrayList<StrayDogModel> doInBackground(String... jsonStr) {
            if(jsonStr != null && jsonStr.length > 0 ) {
                try {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<ArrayList<StrayDogModel>>() {
                    }.getType();
                    ArrayList<StrayDogModel> jsonArr = gson.fromJson(jsonStr[0], listType);
                    return jsonArr;
                } catch (Exception e) {
                    return null;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<StrayDogModel> strayDogModels) {
            super.onPostExecute(strayDogModels);
            if(strayDogModels != null) {
                new ConvertModel().execute(strayDogModels);
                if(prepareCallBack != null)
                    prepareCallBack.onFinished(strayDogModels);
            }
        }
    }

    /**ConvertModel and save data to Realm*/
    private class ConvertModel extends AsyncTask<ArrayList<StrayDogModel> , Void , ArrayList<AnimalRealmObject>> {

        @Override
        protected ArrayList<AnimalRealmObject> doInBackground(ArrayList<StrayDogModel>[] arrayLists) {
            if(arrayLists!=null && arrayLists.length > 0 ){
                Gson gson = new Gson();
                ArrayList<AnimalRealmObject> animalRealmObjects = new ArrayList<AnimalRealmObject>();
                for(StrayDogModel mStrayDogModel : arrayLists[0]){
                    AnimalRealmObject mAnimalRealmObject = new AnimalRealmObject();
                    mAnimalRealmObject.animal_id = mStrayDogModel.animal_id;
                    mAnimalRealmObject.animal_place = mStrayDogModel.animal_place;
                    mAnimalRealmObject.animal_kind = mStrayDogModel.animal_kind;
                    mAnimalRealmObject.animal_opendate = mStrayDogModel.animal_opendate;
                    mAnimalRealmObject.cDate = mStrayDogModel.cDate;
                    mAnimalRealmObject.AllJsonString = gson.toJson(mStrayDogModel);
                    SystemTools.showLog(AnimalRealmName , mAnimalRealmObject.animal_id + " : " +mAnimalRealmObject.AllJsonString);
                    animalRealmObjects.add(mAnimalRealmObject);
                }
                return animalRealmObjects;
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<AnimalRealmObject> animalRealmObjects) {
            super.onPostExecute(animalRealmObjects);
            if(animalRealmObjects!=null && animalRealmObjects.size() > 0){
                AnimalRealm.getInstance(mContext).addAnimal(animalRealmObjects);
            }
        }
    }

    public interface PrepareCallBack{
        public void onFinished(ArrayList<StrayDogModel> jsonArr);
    }

    public void onCancel(){
        if(mParseJsonDataAsync!=null) {
            mParseJsonDataAsync.cancel(true);
        }
    }
}
