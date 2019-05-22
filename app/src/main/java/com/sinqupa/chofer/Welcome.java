package com.sinqupa.chofer;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sdsmdg.tastytoast.TastyToast;
import com.sinqupa.chofer.service.LocationUpdatesService;
import com.sinqupa.chofer.utility.Utility;
import java.util.ArrayList;
import java.util.List;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkPermissions())
                requestPermissions();
        }
    }

    public void Cerrar(View view) {
        finish();
    }

    public void Detener(View view) {
        if (checkPermissions()){
            removeLocationUpdates();
            TastyToast.makeText(getApplicationContext(), "Aplicacion Detenida", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
        }
    }

    public void Iniciar(View view) {
        if (checkPermissions()){
            requestLocationUpdates();
            TastyToast.makeText(getApplicationContext(), "Aplicacion Iniciada", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
        }
    }
    private boolean checkPermissions() {
        for(String permission : Utility.PERMISSIONS){
            if(checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    private void requestPermissions() {
        List<String> remainingPermissions = new ArrayList<>();
        for (String permission : Utility.PERMISSIONS) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED)
                remainingPermissions.add(permission);
        }
        requestPermissions(remainingPermissions.toArray(new String[remainingPermissions.size()]), Utility.REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == Utility.REQUEST_PERMISSIONS_REQUEST_CODE){
            for(int i=0;i<grantResults.length;i++){
                if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                    if(shouldShowRequestPermissionRationale(permissions[i])){
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.activity_welcome),Utility.PERMISSION_TEXT,Snackbar.LENGTH_INDEFINITE);
                        snackbar.setActionTextColor(ContextCompat.getColor(this,R.color.colorDelete));
                        snackbar.setAction(Utility.OK, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                requestPermissions();
                            }
                        });
                        snackbar.show();
                    }
                    return;
                }
            }
        }
    }

    private void requestLocationUpdates() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(this, LocationUpdatesService.class));
        }
        startService(new Intent(this, LocationUpdatesService.class));
    }

    private void removeLocationUpdates() {
        stopService(new Intent(this, LocationUpdatesService.class));
    }


}
