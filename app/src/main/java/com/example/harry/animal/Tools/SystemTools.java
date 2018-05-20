package com.example.harry.animal.Tools;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by Harry on 2018/5/6.
 */

public class SystemTools {
    public static float convertDpToPixel(float dp, Context context){
        float px = dp * getDensity(context);
        return px;
    }

    public static float convertPixelToDp(float px, Context context){
        float dp = px / getDensity(context);
        return dp;
    }

    public static float getDensity(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density;
    }

    public static boolean checkPermission(Context mContext , String PermissionStr){
        if (ContextCompat.checkSelfPermission(mContext, PermissionStr)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            // Show rationale and request permission.
            return false;
        }
    }

    public static void closeKeyBoard(Context mContext , View view) {
        InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        view.setFocusable(false);
//        if(view.isFocused()){
//            view.clearFocus();
//        }
    }

    public static void showLog(String tag , String msg){
        Log.d(tag,msg);
    }

    public static void showToast(Context context , String content) {
        Toast.makeText(context , content , Toast.LENGTH_SHORT).show();
    }
}


