package com.sinqupa.chofer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sdsmdg.tastytoast.TastyToast;
import com.sinqupa.chofer.entidades.Employee;
import com.sinqupa.chofer.entidades.User;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private EditText txtEmail,txtPassword,txtRepeatPassword;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference fireBaseBD;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Para la barra
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registrarse");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtRepeatPassword = (EditText) findViewById(R.id.txtRepeatPassword);
        txtEmail = (EditText) findViewById(R.id.txtEmail);

        firebaseAuth = FirebaseAuth.getInstance();
        fireBaseBD = FirebaseDatabase.getInstance().getReference();
    }

    private void RegisterDatabase(Employee employee){
        Map<String,Object> data = new HashMap<>();
        data.put("latitudeTravel",employee.getLatitudeTravel());
        data.put("longitudeTravel",employee.getLongitudeTravel());
        data.put("code",employee.getCode());
        data.put("activated",employee.getActivated());

        fireBaseBD.child("Employee").child(employee.getUserID()).setValue(data);
        TastyToast.makeText(getApplicationContext(), "Registro Exitoso", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
    }

    private void RegisterAuthentication(User user, final Employee employee){
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    employee.setUserID(firebaseAuth.getCurrentUser().getUid());
                    RegisterDatabase(employee);
                    Intent intent=new Intent(Register.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    TastyToast.makeText(getApplicationContext(), "Ocurrio un Error", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
            }
        });
    }

    public void Save(View view) {
        User user = new User();
        Employee employee = new Employee();
        user.setEmail(txtEmail.getText().toString().trim());
        user.setPassword(txtPassword.getText().toString().trim());
        //validacion de email y password
        String ema=txtEmail.getText().toString();
        String pas=txtPassword.getText().toString();
        String repas=txtRepeatPassword.getText().toString();

        if(TextUtils.isEmpty(ema)){
            Toast.makeText(this,"Debe ingresar un email",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pas)){
            Toast.makeText(this,"Debe ingresar una contraseña",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(repas)){
            Toast.makeText(this,"Debe repetir su contraseña",Toast.LENGTH_SHORT).show();
            return;
        }
        if(pas.equals(repas)){

            if(pas.length()< 6){
                Toast.makeText(this,"La contraseña debe ser mayor de 6 dígitos",Toast.LENGTH_SHORT).show();
                return;
            }
            employee.setLatitudeTravel(0);
            employee.setLongitudeTravel(0);
            employee.setCode(0);
            employee.setActivated(true);
            RegisterAuthentication(user,employee);

        } else {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void Back(View view){
        Intent intent=new Intent(Register.this, MainActivity.class);
        startActivity(intent);
    }
}
