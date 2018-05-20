package com.example.harry.animal.MyAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harry.animal.R;

/**
 * Created by Harry on 2018/5/1.
 */

public class StrayDogViewHolder extends RecyclerView.ViewHolder {

    public ImageView card_img;
    public TextView  card_tx;

    public StrayDogViewHolder(View itemView) {
        super(itemView);
        card_img = (ImageView)itemView.findViewById(R.id.card_img);
        card_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        card_tx = (TextView)itemView.findViewById(R.id.card_tx);
    }

}
