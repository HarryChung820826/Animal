package com.example.harry.animal.Tools;

import android.Manifest;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by Harry on 2018/5/18.
 */

public class LocationTools {
    public static final String LocationToolsName = "LocationToolsName";
    private static final long MIN_TIME = 0;
    private static final float MIN_DUSTANCE = 1;

    Context mContext;
    private LocationManager mLocationManager;
    private String bestProvider = "gps";
    private Criteria criteria ;
    private LocationListener mLocationListener;
    private OnLocationChangeListener mOnLocationChangeListener;
    
    public LocationTools(Context mContext){
        this.mContext = mContext;
        mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        criteria = getCriteria() ;
        bestProvider = mLocationManager.getBestProvider(criteria,true);
        initLocationListener();
    }

    public LocationTools(Context mContext , OnLocationChangeListener mOnLocationChangeListener){
        this(mContext);
        this.mOnLocationChangeListener = mOnLocationChangeListener;
    }


    public void initLocationListener(){
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if(location != null){
                    if(mOnLocationChangeListener != null){
                        mOnLocationChangeListener.LocationChangeCallback(location);
                    }
                    SystemTools.showLog(LocationToolsName ,
                            location.getLatitude() + " , " + location.getLongitude() );
                }else{
                    SystemTools.showLog(LocationToolsName , "location is null");
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
    }

    public void RequestLocation(){
        if(mLocationManager != null) {
            if (SystemTools.checkPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)) {
                mLocationManager.requestLocationUpdates(bestProvider,
                        MIN_TIME,
                        MIN_DUSTANCE,
                        mLocationListener);
            }
        }
    }

    public void UnRequestLocation(){
        if(mLocationManager != null)
            mLocationManager.removeUpdates(mLocationListener);
    }

    private Criteria getCriteria() {
        Criteria criteria = new Criteria();
        // 设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        // 设置是否要求速度
//        criteria.setSpeedRequired(false);
        // 设置是否允许运营商收费
//        criteria.setCostAllowed(false);
        // 设置是否需要方位信息
//        criteria.setBearingRequired(false);
        // 设置是否需要海拔信息
//        criteria.setAltitudeRequired(false);
        // 设置对电源的需求
//        criteria.setPowerRequirement(Criteria.POWER_LOW);
        return criteria;
    }


    public interface OnLocationChangeListener {
        public void LocationChangeCallback(Location location);
    }
}
