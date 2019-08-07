package com.zoyi.sdk_ibeacon_android.sample;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

  private static String IBEACON_PREFS_TAG = "IBEACON_PREFS_TAG";

  private static String IBEACON_STARTED_FLAG_KEY = "IBEACON_STARTED_FLAG_KEY";

  public static boolean isBeaconStarted(Context context) {
    SharedPreferences preferences = context.getSharedPreferences(IBEACON_PREFS_TAG, Context.MODE_PRIVATE);

    return preferences.getBoolean(IBEACON_STARTED_FLAG_KEY, false);
  }

  public static void setBeaconStarted(Context context, boolean value) {
    SharedPreferences preferences = context.getSharedPreferences(IBEACON_PREFS_TAG, Context.MODE_PRIVATE);

    preferences.edit().putBoolean(IBEACON_STARTED_FLAG_KEY, value).apply();
  }
}
