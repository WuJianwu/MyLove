package com.wuijanwu.love.mylove.test;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wuijanwu.love.mylove.R;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private LocationManager mLocManager;
    private Boolean RUN = true;
    private String mMockProviderName = LocationManager.GPS_PROVIDER;


    private double latitude = 31.3029742, longitude = 120.6097126;// 默认常州

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        mLocManager.addTestProvider(LocationManager.GPS_PROVIDER,
//                "requiresNetwork" == "",
//                "requiresSatellite" == "",
//                "requiresCell" == "",
//                "hasMonetaryCost" == "",
//                "supportsAltitude" == "",
//                "supportsSpeed" == "",
//                "supportsBearing" == "",
//                Criteria.NO_REQUIREMENT,
//                Criteria.ACCURACY_COARSE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
//
//        Button webView = (Button) findViewById(R.id.web);
////        webView.loadUrl("http://pxy-disp-sit1.banketech.com/static/Intelligentinstance/index.html");
//        webView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean canMockPosition = Settings.Secure.getInt(getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION, 0) != 0;
//                if (canMockPosition) {
//                    Toast.makeText(MainActivity.this, "true", Toast.LENGTH_SHORT).show();
//                    open();
//                } else {
//                    Toast.makeText(MainActivity.this, "false", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    private void open() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (RUN) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setLocation(longitude, latitude);
                }
            }
        }).start();
    }

    /**
     * setLocation 设置GPS的位置
     */
    private void setLocation(double longitude, double latitude) {
        Location location = new Location(mMockProviderName);
        location.setTime(System.currentTimeMillis());
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setAltitude(2.0f);
        location.setAccuracy(3.0f);
        if (Build.VERSION.SDK_INT > 16) {
            //api 16以上的需要加上这一句才能模拟定位 , 也就是targetSdkVersion > 16
            location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        }
        mLocManager.setTestProviderLocation(mMockProviderName, location);
        Log.e("MainActivity", "setLocation" + String.format("location: x=%s y=%s", latitude, longitude));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RUN = false;
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        Log.e("MainActivity", String.format("location: x=%s y=%s", lat, lng));
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
}
