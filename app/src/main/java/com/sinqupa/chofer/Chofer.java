package com.sinqupa.chofer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chofer extends Fragment {
    public static final String usuario="names";
    View vista;
    TextView txtUser;
    private CircleImageView photoProfile;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vista=inflater.inflate(R.layout.activity_chofer,container,false);
        txtUser=vista.findViewById(R.id.nombre);
        photoProfile=(CircleImageView)vista.findViewById(R.id.profile_image);
        String usuario =getActivity().getIntent().getStringExtra("names");
        txtUser.setText(usuario);

        return vista;
    }

}
