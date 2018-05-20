package com.example.harry.animal;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harry.animal.Tools.LocationTools;
import com.example.harry.animal.Tools.SystemTools;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class LocationPraActivity extends AppCompatActivity implements LocationTools.OnLocationChangeListener ,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener , LocationListener{

    private LocationTools mLocationTools;
    private TextView txv_content;
    private String content = "";
    private Button button;

    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_pra);
        init();
//        initLocationTool();
        initGoogleApiClient();
    }

    private void init(){
        txv_content = ((TextView) findViewById(R.id.txv_content));
        button = ((Button) findViewById(R.id.button));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process();
            }
        });
    }

    private void setContent(String content_inner , boolean StrExtend){
        if(StrExtend){
            this.content += content_inner + "\n";
        }else{
            this.content = content_inner;
        }
        txv_content.setText(content);
        SystemTools.showLog("LocationPraActivity" , content);
    }

    private void initLocationTool(){
        mLocationTools = new LocationTools(LocationPraActivity.this , this);
        mLocationTools.initLocationListener();
    }

    private void processLocationTool(){
        if(mLocationTools != null){
            mLocationTools.RequestLocation();
        }
    }

    private void initGoogleApiClient(){
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    private void process()
    {
        if(mGoogleApiClient != null){
            if(mGoogleApiClient.isConnected()){
                getMyLocation();
            }else{
                Toast.makeText(this,
                        "!mGoogleApiClient.isConnected()", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,
                    "mGoogleApiClient == null", Toast.LENGTH_LONG).show();
        }
    }

    private void getMyLocation(){
        try{
            /* code should explicitly check to see if permission is available
            (with 'checkPermission') or explicitly handle a potential 'SecurityException'
             */
            Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (location != null) {
                setContent(location.getLatitude() + "," + location.getLongitude() , true);
            }else{
                setContent("mLastLocation == null" , true);
            }
        } catch (SecurityException e){
            setContent("SecurityException:" + e.toString() , true);
        }
    }

    protected LocationRequest createLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(200);
        return locationRequest;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mLocationTools != null){
            mLocationTools.RequestLocation();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mLocationTools != null){
            mLocationTools.UnRequestLocation();
        }
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void LocationChangeCallback(Location location) {
        setContent(location.getLatitude() + "," + location.getLongitude() , true);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, createLocationRequest(), this);
            getMyLocation();
        }catch (SecurityException e){
            setContent("SecurityExceptionInConnect:" + e.toString() , true);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        setContent("onConnectionSuspended: " + String.valueOf(i) , true);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        setContent("onConnectionFailed: " + connectionResult.toString() , true);
    }

    @Override
    public void onLocationChanged(Location location) {
        setContent("onLocationChanged " , true);
        setContent(location.getLatitude() + "," + location.getLongitude() , true);
    }
}
