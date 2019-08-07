package com.zoyi.sdk_ibeacon_android.sample;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.zoyi.sdk_ibeacon_android.lib.ZBeaconManager;

public class MainActivity extends AppCompatActivity {

  private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1234;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.buttonStart).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        start();
      }
    });

    findViewById(R.id.buttonStop).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        stopBeacon();
      }
    });
  }

  private void start() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("This app needs location access");
        builder.setMessage("Please grant location access so this app can detect beacons.");
        builder.setPositiveButton(android.R.string.ok, null);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
          @Override
          public void onDismiss(DialogInterface dialog) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
          }
        });
        builder.show();
        return;
      }
    }
    startBeacon();
  }

  private void startBeacon() {
    Prefs.setBeaconStarted(this, true);
    ZBeaconManager.start();
  }

  private void stopBeacon() {
    Prefs.setBeaconStarted(this, false);
    ZBeaconManager.stop();
  }

  @Override
  public void onRequestPermissionsResult(
      int requestCode,
      String permissions[],
      int[] grantResults
  ) {
    switch (requestCode) {
      case PERMISSION_REQUEST_COARSE_LOCATION: {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          startBeacon();
        } else {
          final AlertDialog.Builder builder = new AlertDialog.Builder(this);
          builder.setTitle("Functionality limited");
          builder.setMessage("Location access has not been granted");
          builder.setPositiveButton(android.R.string.ok, null);
          builder.show();
        }
      }
    }
  }
}
