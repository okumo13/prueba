package com.sinqupa.chofer;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sdsmdg.tastytoast.TastyToast;
import com.sinqupa.chofer.service.LocationUpdatesService;
import com.sinqupa.chofer.utility.Utility;
import java.util.ArrayList;
import java.util.List;

public class Welcome extends Fragment {

    //ESTO ES PARA EL BOTON DE INICIAR RECORRIDO
    Button btnIniciar, btnDetener, btnCerrar;
    View vista;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vista=inflater.inflate(R.layout.activity_welcome,container,false);
        btnIniciar=vista.findViewById(R.id.btnStart);
        btnDetener=vista.findViewById(R.id.btnCancelar);
        btnCerrar=vista.findViewById(R.id.btnCerrarSesion);
        //METODOS DE LOS BOTONES

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermissions()){
                    requestLocationUpdates();
                    TastyToast.makeText(getActivity().getApplicationContext(), "Aplicacion Iniciada", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                }
            }
        });
//BOTON DETENER CAUSA CRASH
        // btnDetener.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //     public void onClick(View v) {
            //        if (checkPermissions()){
        //            removeLocationUpdates();
        //             TastyToast.makeText(getActivity().getApplicationContext(), "Aplicacion Detenida", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
        //       }
        //     }
        //   });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return vista;
    }

    //public void Iniciar(View view) {

    //}
    private boolean checkPermissions() {
        for(String permission : Utility.PERMISSIONS){
            if(getActivity().checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    private void requestPermissions() {
        List<String> remainingPermissions = new ArrayList<>();
        for (String permission : Utility.PERMISSIONS) {
            if (getActivity().checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED)
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
                        Snackbar snackbar = Snackbar.make(getView().findViewById(R.id.activity_welcome),Utility.PERMISSION_TEXT,Snackbar.LENGTH_INDEFINITE);
                        snackbar.setActionTextColor(ContextCompat.getColor(getActivity(),R.color.colorDelete));
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
            getActivity().startForegroundService(new Intent(getActivity(), LocationUpdatesService.class));
        }
        getActivity().startService(new Intent(getActivity(), LocationUpdatesService.class));
    }

    private void removeLocationUpdates() {
        getActivity().stopService(new Intent(getActivity(), LocationUpdatesService.class));
    }


}
