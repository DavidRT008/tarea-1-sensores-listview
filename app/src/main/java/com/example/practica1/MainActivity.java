package com.example.practica1;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listSensors;
    private Button btnUpdate;
    private SensorManager gestionSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listSensors = findViewById(R.id.listSensors);
        btnUpdate = findViewById(R.id.btnUpdate);


        gestionSensors = (SensorManager) getSystemService(SENSOR_SERVICE);

        chargeSensors();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chargeSensors();
                Toast.makeText(MainActivity.this, "Lista actualizada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void chargeSensors() {
        List<Sensor> list = gestionSensors.getSensorList(Sensor.TYPE_ALL);
        List<String> nameSensors = new ArrayList<>();

        for (Sensor sensor : list) {
            nameSensors.add(sensor.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                nameSensors
        );

        listSensors.setAdapter(adapter);
    }
}