package com.afr.medicdatafragments.model;

import android.content.Context;

import com.afr.medicdatafragments.database.DatabaseHelper;

import java.util.Date;
import java.util.List;

public class LecturaServicesSQLite implements LecturaServices{

    //Atributos
    private Context contexto;
    private DatabaseHelper myDB;

    //Constructores
    /*public LecturaServicesSQLite(){

    }*/

    //Constructor
    public LecturaServicesSQLite(Context context){
        this.contexto = context;
        myDB = new DatabaseHelper(contexto);
    }

    //Métodos a implementar
    @Override
    public Lectura create(Lectura lectura) {

        return myDB.createLectura(lectura);
    }

    @Override
    public Lectura read(Integer codigo) {
        //TO-DO
        return null;
    }

    @Override
    public Lectura update(Lectura lectura) {
        //TO-DO
        return null;
    }

    @Override
    public boolean delete(Integer codigo) {
        //TO-DO
        return false;
    }

    @Override
    public List<Lectura> getAll() {

        return myDB.getAll();
    }

    @Override
    public List<Lectura> getBetweenDates(Date fecha1, Date fecha2) {

        //TO-DO
        return null;
    }
}
