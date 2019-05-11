package com.sinqupa.chofer;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private EditText txtEmail,txtPassword,txtRepeatPassword;
    private DatabaseReference fireBaseBD;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtRepeatPassword = (EditText) findViewById(R.id.txtRepeatPassword);
        txtEmail = (EditText) findViewById(R.id.txtEmail);

        firebaseAuth = FirebaseAuth.getInstance();
        fireBaseBD = FirebaseDatabase.getInstance().getReference();
        /*fireBaseBD = FirebaseDatabase.getInstance().getReference();

        fireBaseBD.child("Employee").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                     //Employee data =    snapshot.getValue(Employee.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


    }

    private void RegisterDatabase(Employee employee){
        Map<String,Object> data = new HashMap<>();
        data.put("code",employee.getCode());
        data.put("latitudeTravel",employee.getLatitudeTravel());
        data.put("longitudeTravel",employee.getLongitudeTravel());
        data.put("code",employee.getCode());
        data.put("activated",employee.getActivated());

        fireBaseBD.child("Employee").child(employee.getUserID()).setValue(data);
        Toast.makeText(getApplicationContext(),"Se Registro", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getApplicationContext(),"No se registro"  , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Save(View view) {
        User user = new User();
        Employee employee = new Employee();
        user.setEmail(txtEmail.getText().toString().trim());
        user.setPassword(txtPassword.getText().toString().trim());
        employee.setLatitudeTravel("0");
        employee.setLongitudeTravel("0");
        employee.setCode(0);
        employee.setActivated(false);
        RegisterAuthentication(user,employee);
    }
}
