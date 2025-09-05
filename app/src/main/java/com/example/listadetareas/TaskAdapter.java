package com.example.listadetareas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskVH> {

    private ArrayList<Task> data;

    public TaskAdapter(ArrayList<Task> data) {
        this.data = data;
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
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class TaskVH extends RecyclerView.ViewHolder {
        TextView tvTitulo;

        public TaskVH(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
        }
    }
}
