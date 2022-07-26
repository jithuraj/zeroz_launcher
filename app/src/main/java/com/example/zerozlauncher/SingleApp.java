package com.example.zerozlauncher;

import android.graphics.drawable.Drawable;

public class SingleApp {
    String appName;
    Drawable appIcon;
    String appPackageName;

    SingleApp(String appName, Drawable appIcon, String appPackageName){
        this.appName = appName;
        this.appIcon = appIcon;
        this.appPackageName = appPackageName;
    }

}
