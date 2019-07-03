package com.afr.medicdatafragments.services.impl;

import android.content.Context;

import com.afr.medicdatafragments.database.DatabaseHelper;
import com.afr.medicdatafragments.model.Lectura;
import com.afr.medicdatafragments.services.LecturaServices;

import java.util.Date;
import java.util.List;

public class LecturaServicesSQLite implements LecturaServices {

    private DatabaseHelper myDB;

    public LecturaServicesSQLite(Context context){
        myDB = new DatabaseHelper(context);
    }

    @Override
    public Lectura create(Lectura lectura) {
        return myDB.createLectura(lectura);
    }

    @Override
    public Lectura read(Integer codigo) {
        //TODO
        return null;
    }

    @Override
    public Lectura update(Lectura lectura) {
        //TODO
        return null;
    }

    @Override
    public boolean delete(Integer codigo) {
        //TODO
        return false;
    }

    @Override
    public List<Lectura> getAll() {
        return myDB.getAll();
    }

    @Override
    public List<Lectura> getBetweenDates(Date desde, Date hasta) {
        //TODO
        return null;
    }
}
