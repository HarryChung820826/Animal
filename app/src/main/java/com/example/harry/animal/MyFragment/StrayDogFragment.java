package com.example.harry.animal.MyFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.harry.animal.GlobalVar;
import com.example.harry.animal.MainActivity;
import com.example.harry.animal.MyAdapter.StrayDogAdapter;
import com.example.harry.animal.MyModel.DataModel.StrayAnimalModelFactory;
import com.example.harry.animal.MyModel.DataModel.StrayDogModel;
import com.example.harry.animal.R;
import com.example.harry.animal.RealmTools.AnimalRealm;
import com.example.harry.animal.RealmTools.LoadManager;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Harry on 2018/5/1.
 */

public class StrayDogFragment extends Fragment implements MainActivity.onFloatListener {

    private static StrayDogFragment mStrayDogFragment;

    private StrayAnimalModelFactory mStrayAnimalModelFactory;

    private LoadManager mLoadManager;

    public static StrayDogFragment getInstance(){
        if(mStrayDogFragment == null){
            mStrayDogFragment = new StrayDogFragment();
        }
        return mStrayDogFragment;
    }

    private final int DEFAULT_SPAN_COUNT = 1;
    private RecyclerView recyclerView ;
    private GridLayoutManager gridLayoutManager;
    private StrayDogAdapter mStrayDogAdapter;
    private ArrayList<StrayDogModel> allDatas = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadManager = new LoadManager();
        mStrayAnimalModelFactory = new StrayAnimalModelFactory(getActivity());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stray_dog_layout, container, false);
        init(view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mStrayAnimalModelFactory!=null){
            mStrayAnimalModelFactory.onCancel();
        }
    }

    private void init(View view){
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(getActivity(),DEFAULT_SPAN_COUNT);
        mStrayDogAdapter = new StrayDogAdapter(getActivity() , this.allDatas);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mStrayDogAdapter);
    }

    /**
     * 準備資料
     * from realm or internet*/
    private void initData(){
        if(loadingDataFromRealm().size() <= 0)
            loadingData();
    }

    private ArrayList<StrayDogModel> loadingDataFromRealm(){
        ArrayList<StrayDogModel> arrayList = AnimalRealm.getInstance(getActivity()).getAnimalData(
                mLoadManager.getCurrentStartIndex() ,
                mLoadManager.getCurrentEndIndex());
        if(arrayList != null && arrayList.size() > 0){
            refreshUIData(arrayList);
        }
        return arrayList;
    }

    private void loadingData(){
        String url = GlobalVar.STRAY_OPEN_DATA_URL;
        RequestQueue mQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response!=null) {
                            mStrayAnimalModelFactory.parseJsonData(response.toString(), new StrayAnimalModelFactory.PrepareCallBack() {
                                @Override
                                public void onFinished(ArrayList<StrayDogModel> jsonArr) {
                                    if (jsonArr != null) {
                                        refreshUIData(jsonArr);
                                    }
                                }
                            });
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        mQueue.add(mJsonArrayRequest);
    }

    private void refreshUIData(ArrayList<StrayDogModel> jsonArr){
        synchronized (allDatas) {
//            allDatas.clear();
            allDatas.addAll(jsonArr);
            mStrayDogAdapter.refreshData(allDatas);
        }
    }

    @Override
    public void onFloatClick(View view) {
        mLoadManager.NextPage();
        loadingDataFromRealm();
    }
}
