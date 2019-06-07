package com.sinqupa.chofer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sdsmdg.tastytoast.TastyToast;
import com.sinqupa.chofer.service.LocationUpdatesService;
import com.sinqupa.chofer.utility.Utility;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class Chofer extends Fragment {
    public static final String usuario="names";
    View vista;
    TextView txtUser;
    Button btnIniciar, btnDetener, btnCerrar;
    private CircleImageView photoProfile;
    Button btnCerrarSesion;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vista=inflater.inflate(R.layout.activity_chofer,container,false);
        btnCerrarSesion=vista.findViewById(R.id.profile_btnsesion);

        txtUser=vista.findViewById(R.id.nombre);
        photoProfile=(CircleImageView)vista.findViewById(R.id.profile_image);
        String usuario =getActivity().getIntent().getStringExtra("names");
        txtUser.setText(usuario);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        //ESTO ES PARA LOS BOTONES DE

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



        ///////////////////////////////////////////////////////////////////
        return vista;
    }
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
