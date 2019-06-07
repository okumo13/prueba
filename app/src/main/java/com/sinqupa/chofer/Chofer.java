package com.sinqupa.chofer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

import de.hdodenhof.circleimageview.CircleImageView;


public class Chofer extends Fragment {
    public static final String usuario="names";
    View vista;
    TextView txtUser;
    private CircleImageView photoProfile;
    private Button btnStorage;
    private DatabaseReference fireBaseBD;
    private static final int GALLERY_INTENT=1;
    Button btnCerrarSesion;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vista=inflater.inflate(R.layout.activity_chofer,container,false);
        btnCerrarSesion=vista.findViewById(R.id.profile_btnsesion);

        txtUser=vista.findViewById(R.id.nombre);
        photoProfile=(CircleImageView)vista.findViewById(R.id.profile_image);
        String usuario =getActivity().getIntent().getStringExtra("names");
        txtUser.setText(usuario);
        fireBaseBD= FirebaseDatabase.getInstance().getReference();
        btnStorage=(Button)vista.findViewById(R.id.btnfoto);

        btnStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);

            }
        });
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return vista;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == Activity.RESULT_OK){
            Uri uri=data.getData();
            DatabaseReference filePath=fireBaseBD.child("fotos").child(uri.getLastPathSegment());

        }
    }
}
