package com.sinqupa.chofer;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private EditText txtUsername,txtPassword,txtRepeatPassword,txtEmail;
    private DatabaseReference fireBaseBD ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtRepeatPassword = (EditText) findViewById(R.id.txtRepeatPassword);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        fireBaseBD = FirebaseDatabase.getInstance().getReference();

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
        });

    }

    private void AddFirebase(Employee employee){
        Map<String,Object> data = new HashMap<>();
        data.put("username",employee.getUsername());
        data.put("password",employee.getPassword());
        data.put("latitudeTravel",employee.getLatitudeTravel());
        data.put("longitudeTravel",employee.getLongitudeTravel());
        data.put("email",employee.getEmail());
        data.put("code",employee.getCode());
        data.put("activated",employee.getActivated());


        String id = fireBaseBD.push().getKey();
        fireBaseBD.child("Employee").child(id).setValue(data);
        Toast.makeText(getApplicationContext(),"id : " + id, Toast.LENGTH_SHORT).show();
    }

    public void Save(View view) {
        Employee employee = new Employee();
        employee.setUsername(txtUsername.getText().toString());
        employee.setPassword(txtPassword.getText().toString());
        employee.setEmail(txtEmail.getText().toString());
        employee.setLatitudeTravel("0");
        employee.setLongitudeTravel("0");
        employee.setCode(0);
        employee.setActivated(false);

        AddFirebase(employee);
    }
}
