package com.afr.customeventdemo;

import java.util.Timer;
import java.util.TimerTask;

public class CustomObject {

    private String nombre;

    //Definición del listener (es una clase interna)
    public interface MyCustomObjectListener{
        public void onDataLoaded(String data);
    }

    //Variable de instancia que almacena la implementación del listener
    private MyCustomObjectListener listener;

    //Constructor
    public CustomObject(String nombre){
        this.nombre = nombre;
        this.listener = null; //Es innecesario, pero así visualizamos que es 'null'
        // Ponemos en marcha esa tarea asíncrona tan importante...

        tareaAsincrona();
    }

    //Setter que permite la implementación
    public void setMyCustomObjectListener(MyCustomObjectListener listener){
        this.listener = listener;
    }

    //Método asíncrono
    private void tareaAsincrona(){

        final int tiempoAleatorio = 1000 + (int) (Math.random() * 3000);

        //Vamos a provocar la ejecución cíclica del código cada 3 segundos...
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (listener != null){
                    int numeroAleatorio = (int) (Math.random() * 1000);

                    listener.onDataLoaded(nombre + ": " + tiempoAleatorio + " : "+ numeroAleatorio);
                }
            }
        }, 0,tiempoAleatorio);
    }

}
