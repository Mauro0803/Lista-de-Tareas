package com.example.listadetareas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity implements TaskAdapter.TaskCallbacks {

    private RecyclerView rvTareas;
    private FloatingActionButton fabAgregar;

    // Datos de prueba (luego los reemplazamos)
    private ArrayList<Task> tareas = new ArrayList<>();
    private TaskAdapter adapter;

    private static final String PREFS = "TODO_PREFS";
    private static final String KEY_JSON = "TASKS_JSON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTareas = findViewById(R.id.rvTareas);
        fabAgregar = findViewById(R.id.fabAgregar);

        // Configura la lista para que se muestre en columna
        rvTareas.setLayoutManager(new LinearLayoutManager(this));

        // Cargar desde SharedPreferences (si no hay, queda lista vacía)
        cargarTareas();

        adapter = new TaskAdapter(tareas, this);
        rvTareas.setAdapter(adapter);

        // Ordena lo cargado (si existía algo persistido)
        ordenarYRefrescar();

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
                        Task nueva = new Task(titulo, false);
                        tareas.add(nueva);
                        guardarTareas();
                        ordenarYRefrescar();

                        // Lleva la lista hasta la nueva tarea (índice tras ordenar)
                        int idx = tareas.indexOf(nueva); // funciona por referencia
                        if (idx >= 0) {
                            rvTareas.smoothScrollToPosition(idx);
                        }
                    }
                })

                .setNegativeButton("Cancelar", null)
                .show();
    }

    // ====== Callbacks del adapter ======
    @Override
    public void onTaskCheckedChanged(int position, boolean isChecked) {
        tareas.get(position).setDone(isChecked);
        guardarTareas();
        ordenarYRefrescar(); // <- reordena tras cambiar el estado
    }

    @Override
    public void onTaskDelete(int position) {
        tareas.remove(position);
        adapter.notifyItemRemoved(position);
        guardarTareas();
    }

    // ====== Persistencia simple con JSON en SharedPreferences ======
    private void guardarTareas() {
        try {
            JSONArray arr = new JSONArray();
            for (Task t : tareas) {
                JSONObject o = new JSONObject();
                o.put("title", t.getTitle());
                o.put("done", t.isDone());
                arr.put(o);
            }
            SharedPreferences sp = getSharedPreferences(PREFS, MODE_PRIVATE);
            sp.edit().putString(KEY_JSON, arr.toString()).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarTareas() {
        tareas.clear();
        SharedPreferences sp = getSharedPreferences(PREFS, MODE_PRIVATE);
        String json = sp.getString(KEY_JSON, "[]");
        try {
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                tareas.add(new Task(o.getString("title"), o.getBoolean("done")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ordenarYRefrescar() {
        // incompletas (done=false) antes que completadas (done=true)
        Collections.sort(tareas, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                int byDone = Boolean.compare(t1.isDone(), t2.isDone()); // false < true
                if (byDone != 0) return byDone;
                // secundario: alfabético por título (ignora mayúsculas)
                return t1.getTitle().compareToIgnoreCase(t2.getTitle());
            }
        });
        adapter.notifyDataSetChanged();
    }

}
