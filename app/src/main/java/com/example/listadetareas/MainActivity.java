package com.example.listadetareas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;

import android.text.InputType;
import android.widget.EditText;


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

        // Conecta el FAB a un diálogo
        fabAgregar.setOnClickListener(v -> mostrarDialogoAgregar());

    }

    // Metodo para mostrar el diálogo y agregar la tarea
    private void mostrarDialogoAgregar() {
        final EditText input = new EditText(this);
        input.setHint("Ej: Comprar pan");
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        new AlertDialog.Builder(this)
                .setTitle("Nueva tarea")
                .setView(input)
                .setPositiveButton("Agregar", (dialog, which) -> {
                    String titulo = input.getText().toString().trim();
                    if (!titulo.isEmpty()) {
                        // Agregar a la lista y notificar al adapter
                        tareas.add(new Task(titulo, false));
                        adapter.notifyItemInserted(tareas.size() - 1);
                        rvTareas.smoothScrollToPosition(tareas.size() - 1);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

}