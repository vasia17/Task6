package com.example.shon.boost4.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.shon.boost4.MainActivity;
import com.example.shon.boost4.Measurement;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AccelerometerService extends Service implements SensorEventListener {

    private long lastUpdate = 0;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private int mCounter = 0;
    private Firebase mSamplesRef;

    public class AccelerometerBinder extends Binder {
        public AccelerometerService getService() {
            Log.d(MainActivity.MAIN_TAG, "AccelerometerBinder: getService");
            return AccelerometerService.this;
        }
    }

    private final IBinder mBinder = new AccelerometerBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(MainActivity.MAIN_TAG, "onStartCommand: Received start id " + startId + ": " + intent);
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(MainActivity.MAIN_TAG, "AccelerometerService: onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);

        MainActivity.mFireBaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mCounter = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public void onDestroy() {
        Log.d(MainActivity.MAIN_TAG, "AccelerometerService: onDestroy");
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d(MainActivity.MAIN_TAG, "AccelerometerService: onSensorChanged " + String.valueOf(event.values[0]));
        long curTime = System.currentTimeMillis();
        if ((curTime - lastUpdate) > MainActivity.TIME_INTERVAL) {
            mSamplesRef = MainActivity.mFireBaseRef.child("parameters" + mCounter);
            Measurement s = new Measurement(event.values[0], event.values[1], event.values[2]);
            mSamplesRef.setValue(s);
            mCounter++;
            lastUpdate = curTime;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}