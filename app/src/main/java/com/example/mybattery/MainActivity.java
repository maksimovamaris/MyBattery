package com.example.mybattery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements IBatteryListener{
private static final int CLIP_DRAWABLE_MAX_LEVEL=10000;
    private ImageView mBatteryLevel;
    private ImageView mFlash;
private BatteryBroadcastReceiver mReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }
    @Override
    protected void onStart()
    {
        super.onStart();
        setupBroadcastReceiver();

    }

    @Override
    protected void onStop()
    {
        super.onStop();
        destroyBroadcastReceiver();
    }

    private void setupBroadcastReceiver()
    {
        IntentFilter filter=new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        mReceiver=new BatteryBroadcastReceiver(this);
        registerReceiver(mReceiver,filter);
    }

    private void destroyBroadcastReceiver()
    {
        unregisterReceiver(mReceiver);
        mReceiver=null;
    }

    private void initViews()
    {
        mBatteryLevel=findViewById(R.id.img_battery);
        mFlash=findViewById(R.id.img_flash);
    }

    @Override
    public void onBatteryChanged(BatteryInfo batteryInfo)
    {
            mBatteryLevel.setImageLevel((int)(batteryInfo.getLevel()*CLIP_DRAWABLE_MAX_LEVEL));
            if (batteryInfo.isCharging())
            mFlash.setVisibility(View.VISIBLE);
            else
                mFlash.setVisibility(View.GONE);
        }

}
