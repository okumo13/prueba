package com.sinqupa.chofer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sinqupa.chofer.utility.Utility;

import java.util.HashMap;
import java.util.Map;

public class Welcome extends AppCompatActivity {
    private DatabaseReference fireBaseBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        fireBaseBD = FirebaseDatabase.getInstance().getReference();
    }

    public void Cerrar(View view) {
    }

    public void Detener(View view) {
    }

    public void Iniciar(View view) {
        Employee employee = new Employee();
        employee.setLongitudeTravel("123456789");
        employee.setLatitudeTravel("987654321");
        employee.setCode(0);
        employee.setActivated(true);
        UpdateDatabase(employee);
    }

    private void UpdateDatabase(Employee employee){
        Map<String,Object> data = new HashMap<>();

        data.put("latitudeTravel",employee.getLatitudeTravel());
        data.put("longitudeTravel",employee.getLongitudeTravel());
        data.put("code",employee.getCode());
        data.put("activated",employee.getActivated());
        fireBaseBD.child("Employee").child(Utility.userID).setValue(data);

    }
}
