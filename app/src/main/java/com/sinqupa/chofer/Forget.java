package com.sinqupa.chofer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.sdsmdg.tastytoast.TastyToast;

public class Forget extends AppCompatActivity {
    private EditText txtEmailForget;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        txtEmailForget = (EditText) findViewById(R.id.txtEmailForget);
        firebaseAuth = FirebaseAuth.getInstance();

        //Para la barra
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Recuperar contraseña");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void ResetPassword(String email){
        firebaseAuth.setLanguageCode("es");
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    TastyToast.makeText(getApplicationContext(), "Email Enviado", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
                }else {
                    TastyToast.makeText(getApplicationContext(), "Ocurrio un Error", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                }
            }
        });
    }

    public void SendEmail(View view) {
        String email = txtEmailForget.getText().toString();
        if (!email.isEmpty()){
            ResetPassword(email);
        }else {
            TastyToast.makeText(getApplicationContext(), "Ingresar Correo", TastyToast.LENGTH_LONG, TastyToast.WARNING);
        }
    }

    public void Back(View view) {
        Intent intent=new Intent(Forget.this,MainActivity.class);
        startActivity(intent);
    }
}
