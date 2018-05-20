package com.example.harry.animal.MyAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.harry.animal.AnimalDetailActivity;
import com.example.harry.animal.GlobalVar;
import com.example.harry.animal.MyModel.DataModel.StrayDogModel;
import com.example.harry.animal.R;

import java.util.ArrayList;

/**
 * Created by Harry on 2018/5/1.
 */

public class StrayDogAdapter extends RecyclerView.Adapter<StrayDogViewHolder> {

    private Activity activity;
    private ArrayList<StrayDogModel> allDatas = new ArrayList<>();

    public StrayDogAdapter(Activity activity , ArrayList<StrayDogModel> allDatas){
        this.activity = activity;
        this.allDatas.clear();
        this.allDatas.addAll(allDatas);
    }

    @Override
    public StrayDogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_stray_dog_layout, parent, false);
        return new StrayDogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StrayDogViewHolder holder, int position) {
        setViewHolder(holder , position);
    }

    @Override
    public int getItemCount() {
        return allDatas.size();
    }

    public void refreshData(ArrayList<StrayDogModel> allDatas){
        this.allDatas.clear();
        this.allDatas.addAll(allDatas);
        if(this.allDatas!=null && this.allDatas.size()>0)
            notifyDataSetChanged();
    }

    private void setViewHolder(final StrayDogViewHolder viewHolder, int position){
        final StrayDogModel animalObj = allDatas.get(position);
        viewHolder.card_tx.setText(animalObj.animal_id + "");
        Glide.with(activity)
                .load(animalObj.album_file)
                .crossFade()
                .into(viewHolder.card_img);

        viewHolder.card_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, AnimalDetailActivity.class);
                Bundle bd = new Bundle();
                bd.putSerializable(GlobalVar.BUNDLE_DETAIL_DATA_TAG,animalObj);
                intent.putExtras(bd);
                activity.startActivity(intent);
            }
        });
    }
}
