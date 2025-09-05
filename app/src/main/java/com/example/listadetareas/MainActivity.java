package com.example.listadetareas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTareas;
    private FloatingActionButton fabAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTareas = findViewById(R.id.rvTareas);
        fabAgregar = findViewById(R.id.fabAgregar);

        // Configura la lista para que se muestre en columna
        rvTareas.setLayoutManager(new LinearLayoutManager(this));

        // Todavía no asignamos adapter (Parte 2)
        // rvTareas.setAdapter(...);

        // El FAB por ahora no hace nada (lo conectamos en la Parte 3)
        // fabAgregar.setOnClickListener(v -> { ... });
    }
}