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
                        guardarTareas();
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
}
