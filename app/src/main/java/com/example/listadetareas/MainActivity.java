package com.example.listadetareas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTareas;
    private FloatingActionButton fabAgregar;

    // Datos de prueba (luego los reemplazamos)
    private ArrayList<Task> tareas = new ArrayList<>();
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTareas = findViewById(R.id.rvTareas);
        fabAgregar = findViewById(R.id.fabAgregar);

        // Configura la lista para que se muestre en columna
        rvTareas.setLayoutManager(new LinearLayoutManager(this));

        // ---- Datos “hardcodeados” de prueba ----
        tareas.add(new Task("Comprar pan", false));
        tareas.add(new Task("Estudiar 30 min Android", false));
        tareas.add(new Task("Enviar correo de seguimiento", false));
        // ----------------------------------------

        adapter = new TaskAdapter(tareas);
        rvTareas.setAdapter(adapter);

        // El FAB por ahora no hace nada (lo conectamos en la Parte 3)
        // fabAgregar.setOnClickListener(v -> { ... });
    }
}