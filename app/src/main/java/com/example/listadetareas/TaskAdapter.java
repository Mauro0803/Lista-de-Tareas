package com.example.listadetareas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.graphics.Paint;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskVH> {

    public interface TaskCallbacks {
        void onTaskCheckedChanged(int position, boolean isChecked);
        void onTaskDelete(int position);
    }
    private final ArrayList<Task> data;
    private final TaskCallbacks callbacks;

    public TaskAdapter(ArrayList<Task> data, TaskCallbacks callbacks) {
        this.data = data;
        this.callbacks = callbacks;
    }

    @NonNull
    @Override
    public TaskVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarea, parent, false);
        return new TaskVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskVH holder, int position) {
        Task t = data.get(position);
        holder.tvTitulo.setText(t.getTitle());
        // Estilo inicial según estado
        applyDoneStyle(holder.tvTitulo, t.isDone());


        // Evitar “rebote” del listener al reciclar
        holder.cbDone.setOnCheckedChangeListener(null);
        holder.cbDone.setChecked(t.isDone());
        holder.cbDone.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (callbacks != null) callbacks.onTaskCheckedChanged(holder.getAdapterPosition(), isChecked);

            // ✅ Actualiza el estilo inmediatamente al marcar/desmarcar
            applyDoneStyle(holder.tvTitulo, isChecked);
        });


        holder.btnBorrar.setOnClickListener(v -> {
            if (callbacks != null) callbacks.onTaskDelete(holder.getAdapterPosition());
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class TaskVH extends RecyclerView.ViewHolder {
        CheckBox cbDone;
        TextView tvTitulo;
        ImageButton btnBorrar;

        public TaskVH(@NonNull View itemView) {
            super(itemView);
            cbDone = itemView.findViewById(R.id.cbDone);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            btnBorrar = itemView.findViewById(R.id.btnBorrar);
        }
    }

    private void applyDoneStyle(TextView tv, boolean done) {
        if (done) {
            tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tv.setAlpha(0.6f);   // opcional: atenuar
        } else {
            tv.setPaintFlags(tv.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            tv.setAlpha(1.0f);
        }
    }

}