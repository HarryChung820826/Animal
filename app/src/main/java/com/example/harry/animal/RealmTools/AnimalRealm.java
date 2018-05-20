package com.example.harry.animal.RealmTools;

import android.content.Context;

import com.example.harry.animal.MyModel.DataModel.StrayDogModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Harry on 2017/4/29.
 */
public class AnimalRealm {

    private RealmConfiguration realmConfiguration ;
    private Context mContext;
    private Realm realm;
    public static AnimalRealm animalRealm;


    public static AnimalRealm getInstance(Context mContext){
        if(animalRealm==null){
            animalRealm = new AnimalRealm(mContext);
        }
        return animalRealm;
//        return new AnimalRealm(mContext);
    }

    AnimalRealm(Context mContext){
        this.mContext = mContext;
        Realm.init(mContext);
        realmConfiguration = new RealmConfiguration.Builder()
                .name(RealmConfig.AnimalRealmName)
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    private Realm getRealm(){
        return Realm.getInstance(realmConfiguration);
    }

    private void closeRealm(){
        if(this.realm!=null) {
            if(!this.realm.isClosed()){
                this.realm.close();
            }
        }
    }

    public ArrayList<StrayDogModel> getAnimalData(int startIndex , int endIndex){
        this.realm = getRealm();
        RealmQuery<AnimalRealmObject> realmQuery = this.realm.where(AnimalRealmObject.class);
        RealmResults<AnimalRealmObject> realmResults = realmQuery.findAll();

        ArrayList<StrayDogModel> result = new ArrayList<StrayDogModel>();
        if(startIndex <= realmResults.size() - 1){
            int index = startIndex;
            do{
                result.add(realmResults.get(index).ConvertToAnimalObj());
                index += 1;
            }while (index < endIndex && index <= realmResults.size() - 1);
        }
        closeRealm();
        return result;
    }

    public ArrayList<StrayDogModel> getDogData(){
        this.realm = getRealm();
        RealmQuery<AnimalRealmObject> realmQuery = this.realm.where(AnimalRealmObject.class)
                .equalTo("animal_kind","狗");
        RealmResults<AnimalRealmObject> realmResults = realmQuery.findAll();
        ArrayList<StrayDogModel> result = new ArrayList<StrayDogModel>();
        for(AnimalRealmObject animalRealmObject : realmResults){
            result.add(animalRealmObject.ConvertToAnimalObj());
        }
        closeRealm();
        return result;
    }

    public ArrayList<StrayDogModel> getCatData(){
        this.realm = getRealm();
        RealmQuery<AnimalRealmObject> realmQuery = this.realm.where(AnimalRealmObject.class)
                .equalTo("animal_kind","貓");
        RealmResults<AnimalRealmObject> realmResults = realmQuery.findAll();
        ArrayList<StrayDogModel> result = new ArrayList<StrayDogModel>();
        for(AnimalRealmObject animalRealmObject : realmResults){
            result.add(animalRealmObject.ConvertToAnimalObj());
        }
        closeRealm();
        return result;
    }

    public synchronized void addAnimal(final ArrayList<AnimalRealmObject> animalObjArrayList){
        this.realm = getRealm();
        this.realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(animalObjArrayList);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                closeRealm();
            }
        },new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error) {
                closeRealm();
            }
        });
    }

    public void deleteAll(){
        this.realm = getRealm();
        this.realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
                closeRealm();
            }
        });
    }
}
