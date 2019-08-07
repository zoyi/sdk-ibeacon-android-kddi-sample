package com.zoyi.sdk_ibeacon_android.sample;

import androidx.multidex.MultiDexApplication;
import com.zoyi.sdk_ibeacon_android.lib.Target;
import com.zoyi.sdk_ibeacon_android.lib.ZBeaconManager;

public class App extends MultiDexApplication {

  private String email = "EMAIL";
  private String authToken = "TOKEN";

  @Override
  public void onCreate() {
    super.onCreate();

    ZBeaconManager.init(this, email, authToken, Target.PRODUCTION);
    ZBeaconManager.setDebugMode(true);

    if (Prefs.isBeaconStarted(this)) {
      ZBeaconManager.start();
    }
  }
}
