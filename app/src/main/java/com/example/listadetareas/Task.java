package com.example.listadetareas;

public class Task {

    // Declarar Variables
    private String title;
    private boolean done; // lo usaremos después

    // Constructor con argumentos
    public Task(String title, boolean done) {
        this.title = title;
        this.done = done;
    }
    // Metodos
    public String getTitle() { return title; }
    public boolean isDone() { return done; }

    public void setTitle(String title) { this.title = title; }
    public void setDone(boolean done) { this.done = done; }
}
