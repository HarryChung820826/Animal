package com.example.harry.animal.TouchEventPra;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.harry.animal.R;
import com.example.harry.animal.Tools.SystemTools;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Harry on 2018/5/20.
 */

public class TouchRecyclerViewAdapter extends RecyclerView.Adapter {

    private final int ItemCount = 10 ;
    private Context mContext;

    public TouchRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.touch_list_pic_item, parent, false);
        return new TouchPicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof TouchPicViewHolder){
            if(((TouchPicViewHolder)holder).item != null){
                ((TouchPicViewHolder)holder).item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SystemTools.showToast(mContext , "TouchPicViewHolderPic "+ position);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return ItemCount;
    }

    class TouchPicViewHolder extends RecyclerView.ViewHolder{

        CircleImageView item;

        public TouchPicViewHolder(View itemView) {
            super(itemView);
            item = (CircleImageView)itemView.findViewById(R.id.item);
        }
    }

}
