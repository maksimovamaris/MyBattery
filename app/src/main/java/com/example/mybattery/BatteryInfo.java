package com.example.mybattery;

public class BatteryInfo {
    private float level;
    private boolean isCharging;

    public BatteryInfo(float level, boolean isCharging)
    {
        this.level = level;
        this.isCharging = isCharging;
    }

    public float getLevel() {
        return level;
    }

    public boolean isCharging() {
        return isCharging;
    }
}
