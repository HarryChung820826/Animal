package com.example.harry.animal.MyModel.ViewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.harry.animal.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Harry on 2018/5/6.
 */

public class MainFunItemViewModel {
    View view ;
    private Context mContext;
    private LinearLayout item_layout;
    private CircleImageView item_img_layout;
    private TextView item_name_layout;

    private String funName;
    private String FunImgURL;
    private int picID;
    private View.OnClickListener onFunClickListener;

    public MainFunItemViewModel(Context mContext , ViewGroup parent){
        this.mContext = mContext;
        view = LayoutInflater.from(mContext).inflate(R.layout.main_function_option_layout, parent, false);
        if(view != null)
            init(view);
    }

    private void init(View view){
        item_layout = ((LinearLayout) view.findViewById(R.id.item_layout));
        item_img_layout = ((CircleImageView) view.findViewById(R.id.item_img_layout));
        item_name_layout = ((TextView) view.findViewById(R.id.item_name_layout));
    }

    public MainFunItemViewModel setFunName(String funName){
        this.funName = funName;
        return this;
    }

    public MainFunItemViewModel setFunImg(String url){
        this.FunImgURL = url;
        return this;
    }

    public MainFunItemViewModel setFunImgID(int picID){
        this.picID = picID;
        return this;
    }

    public MainFunItemViewModel setFunClickListener(View.OnClickListener onClickListener){
        this.onFunClickListener = onClickListener;
        return this;
    }

    public View build(){
        if(funName!=null && item_name_layout != null)
            item_name_layout.setText(funName);

        if(picID > 0){
            item_img_layout.setImageDrawable(mContext.getResources().getDrawable(picID));
        }else {
            if (FunImgURL != null && item_img_layout != null) {
                Glide.with(mContext)
                        .load(FunImgURL)
                        .into(item_img_layout);
            }
        }

        if(onFunClickListener!=null && item_layout!=null)
            item_layout.setOnClickListener(onFunClickListener);

        return view;
    }

    public static class MainFunItemViewVarDataModel{
        public String funName;
        public String FunImgURL;
        public int picID ;
        public View.OnClickListener onFunClickListener;

        private MainFunItemViewVarDataModel(String funName, View.OnClickListener onFunClickListener) {
            this.funName = funName;
            this.onFunClickListener = onFunClickListener;
        }

        public MainFunItemViewVarDataModel(String funName, String funImgURL, View.OnClickListener onFunClickListener) {
            this(funName , onFunClickListener);
            FunImgURL = funImgURL;
        }

        public MainFunItemViewVarDataModel(String funName, int picId, View.OnClickListener onFunClickListener) {
            this(funName , onFunClickListener);
            picID = picId;
        }

    }

}
