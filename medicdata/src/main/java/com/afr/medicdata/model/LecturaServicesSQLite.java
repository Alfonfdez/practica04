package com.afr.medicdata.model;

import android.content.Context;
import android.database.Cursor;

import com.afr.medicdata.database.DatabaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LecturaServicesSQLite implements LecturaServices{

    //private static final LecturaServicesSQLite INSTANCE = new LecturaServicesSQLite();

    private Context contexto;
    private DatabaseHelper myDB;


    //Constructores
    /*public LecturaServicesSQLite(){

    }*/

    public LecturaServicesSQLite(Context context){
        this.contexto = context;
        myDB = new DatabaseHelper(contexto);
    }

    /*public static LecturaServicesSQLite getInstance() {
        return INSTANCE;
    }*/

    //Métodos a implementar
    @Override
    public Lectura create(Lectura lectura) {

        /*SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");

        Date fecha = lectura.getFecha();
        Date hora = lectura.getHora();

        String strParteFecha = sdfFecha.format(fecha);
        String strParteHora = sdfHora.format(hora);

        double peso = lectura.getPeso();
        double diastolica = lectura.getDiastolica();
        double sistolica = lectura.getSistolica();

        myDB.insertData(strParteFecha, strParteHora, peso, diastolica, sistolica);

        return lectura;*/
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

        /*List<Lectura> lecturas = new ArrayList<>();

        //FALLA AQUÍ
        Cursor cursor = myDB.getAll();

        //if(cursor == null || cursor.getCount() == 0){
            //return lecturas;
        //}

        while(cursor.moveToNext()){

            int codigo = cursor.getInt(0);
            String fecha = cursor.getString(1);
            String hora = cursor.getString(2);
            double peso = cursor.getDouble(3);
            double diastolica = cursor.getDouble(4);
            double sistolica = cursor.getDouble(5);

            //String query = codigo + ": " + fecha + " " + hora + " " + peso + " " + diastolica + " " + sistolica;

            //Log.d("DATABASE", query);

            try {
                Date dateFecha = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
                Date dateHora = new SimpleDateFormat("HH/mm").parse(hora);

                Lectura lectura = new Lectura(dateFecha, dateHora, peso, diastolica, sistolica);
                lectura.setCodigo(codigo);

                lecturas.add(lectura);

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return lecturas;*/

        return myDB.getAll();
    }

    @Override
    public List<Lectura> getBetweenDates(Date fecha1, Date fecha2) {

        //TO-DO
        return null;
    }
}
