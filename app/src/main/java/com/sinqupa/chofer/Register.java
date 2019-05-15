package com.sinqupa.chofer;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        employee.setLatitudeTravel(0);
        employee.setLongitudeTravel(0);
        employee.setCode(0);
        employee.setActivated(true);
        RegisterAuthentication(user,employee);
    }
}
