package com.sinqupa.chofer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sdsmdg.tastytoast.TastyToast;
import com.sinqupa.chofer.entidades.User;
import com.sinqupa.chofer.utility.Utility;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText txtEmail,txtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEmail = (EditText)findViewById(R.id.txtEmailLogin);
        txtPassword = (EditText) findViewById(R.id.txtPasswordLogin);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void Register(View view) {
        Intent intent = new Intent(MainActivity.this,Register.class);
        startActivity(intent);
    }

    public void Ingresar(View view) {
        User user = new User();
        user.setEmail(txtEmail.getText().toString());
        user.setPassword(txtPassword.getText().toString());
        //validacion de email y pass
        String ema=txtEmail.getText().toString();
        String pas=txtPassword.getText().toString();
        if(TextUtils.isEmpty(ema)){
            Toast.makeText(this,"Debe ingresar un email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pas)){
            Toast.makeText(this,"Debe ingresar una contraseña",Toast.LENGTH_SHORT).show();
        return;
        }


        Login(user);
    }

    private void Login(final User user){
        firebaseAuth.signInWithEmailAndPassword(user.getEmail(),user.getPassword()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Utility.userID = firebaseAuth.getCurrentUser().getUid();
                    Intent intent = new Intent(MainActivity.this,Welcome.class);
                    startActivity(intent);
                }
                else{
                    TastyToast.makeText(getApplicationContext(), "Datos Incorrectos", TastyToast.LENGTH_LONG, TastyToast.CONFUSING);
                }
            }
        });
    }

    public void ForgetPassword(View view) {
        Intent intent = new Intent(MainActivity.this,Forget.class);
        startActivity(intent);
    }
}
