package com.afr.medicdata.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.afr.medicdata.model.Lectura;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//DatabaseHelper ES-UN SQLiteOpenHelper
public class DatabaseHelper extends SQLiteOpenHelper {

    //Hace falta crear un Constructor que invoque al Constructor padre (SQLiteOpenHelper) con un argumento (aparte de otros): el nombre de la base de datos.
    //ESTE CONSTRUCTOR VACÍO NO NOS VALDRÁ
    /*public DatabaseHelper(){
        super();
    }*/

    public static final String DATABASE_NAME = "medicdata.db";

    public static final String MEDICDATA_TABLE = "MEDICDATA";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "FECHA";
    public static final String COL_3 = "HORA";
    public static final String COL_4 = "PESO";
    public static final String COL_5 = "DIASTOLICA";
    public static final String COL_6 = "SISTOLICA";

    private static final SimpleDateFormat SDF_FECHA = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat SDF_HORA = new SimpleDateFormat("HH:mm");

    //Uno de los 3 Constructores del Constructor padre (SQLiteOpenHelper)
    /*public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }*/

    //Constructor
    //'Harcodeamos' 3 argumentos: name, factory, version
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("DATABASE", "PRIMERO - CONSTRUCTOR");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Entramos en este método cuando la base de datos se crea por primera vez
        //Se tendrá que ejecutar una sentencia de DDL (Data Definition Language) (CREATE, DROP, ALTER) de SQL (Structured Query Language).

        /*
         CREATE TABLE AMIGOS (
           ID          INTEGER PRIMARY KEY AUTOINCREMENT,
           NOMBRE      TEXT NOT NULL,
           APELLIDO1   TEXT NOT NULL,
           APELLIDO2   TEXT
         )
        */

        Log.d("DATABASE", "SEGUNDO - ONCREATE");

        StringBuilder strSQL = new StringBuilder();

        strSQL.append("CREATE TABLE ").append(MEDICDATA_TABLE).append(" ").append(" (")
                .append(COL_1).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COL_2).append(" TEXT NOT NULL, ")
                .append(COL_3).append(" TEXT NOT NULL, ")
                .append(COL_4).append(" REAL NOT NULL, ")
                .append(COL_5).append(" REAL NOT NULL, ")
                .append(COL_6).append(" REAL NOT NULL")
                .append(")");

        Log.d("DATABASE", "TERCERO - ONCREATE");
        Log.d("DATABASE", strSQL.toString());

        String strDDL = strSQL.toString();

        db.execSQL(strDDL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Entramos aquí cuando se detecta un cambio de versión en la base de datos
        //Normalmente esto conlleva la creación de nuevas tablas o columnas en tablas ya existentes
        //DROP TABLE elimina la tabla y "onCreate" vuelve a crear el esquema desde cero
        db.execSQL("DROP TABLE IF EXISTS " + MEDICDATA_TABLE);
        onCreate(db);
    }


    //Métodos para ser implementados por otras clases
    /*public Lectura createLectura(Date fecha, Date hora, double peso, double diastolica, double sistolica){

        return null;
    }*/


    public Lectura createLectura(Lectura lectura){

        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, getStringFromDate(lectura.getFecha()));
        contentValues.put(COL_3, getStringFromHour(lectura.getHora()));
        contentValues.put(COL_4, lectura.getPeso());
        contentValues.put(COL_5, lectura.getDiastolica());
        contentValues.put(COL_6, lectura.getSistolica());

        // 'nullColumnHack' se utiliza cuando pretendemos insertar un registro con valores "null". En ese caso 'contentValues' estará vacío (SIN 'put')
        long resultado = db.insert(MEDICDATA_TABLE, null, contentValues);

        //A la lectura que me llegaba sin código, le asigno un código y la devuelvo
        lectura.setCodigo((int)resultado);

        return resultado == -1 ? null : lectura;
    }

    public Lectura getLectura(int codigo){

        SQLiteDatabase db = getWritableDatabase();

        String[] args = new String[] {String.valueOf(codigo)};

        Cursor cursor = db.rawQuery("SELECT * FROM " + MEDICDATA_TABLE + " WHERE CODIGO = ?", args);

        Lectura lectura = null;

        if (cursor != null && cursor.getCount() > 0){   // Sabemos que hay 1 registro!
            cursor.moveToNext();                        // Situamos el puntero en ese registro

            int code = cursor.getInt(0);
            Date fecha = getDateFromString(cursor.getString(1));
            Date hora = getHourFromString(cursor.getString(2));
            double peso = cursor.getDouble(3);
            double diastolica = cursor.getDouble(4);
            double sistolica = cursor.getDouble(5);

            lectura = new Lectura(fecha,hora,peso,diastolica,sistolica);
            lectura.setCodigo(code);

        }

        return lectura;

    }

    //Coger Fecha en orden descendente
    /*public Cursor getAll2(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MEDICDATA_TABLE + " ORDER BY " + COL_1 + " DESC", null);

        return cursor;
    }*/

    public List<Lectura> getAll(){

        SQLiteDatabase db = getWritableDatabase();

        //Cursor cursor = db.rawQuery("SELECT * FROM " + MEDICDATA_TABLE + " ORDER BY " + COL_1 + " DESC", null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + MEDICDATA_TABLE, null);

        String[] columnas = new String[]{COL_1,COL_2,COL_3,COL_4,COL_5,COL_6};

        List<Lectura> lecturas = new ArrayList<Lectura>();

        if (cursor != null && cursor.getCount() > 0){

            while (cursor.moveToNext()){

                Integer codigo = cursor.getInt(0);
                String strFecha = cursor.getString(1); // La fecha vive como String "dd/MM/yyyy HH:mm"
                Date fecha = getDateFromString(strFecha);
                String strHora = cursor.getString(2); // La fecha vive como String "dd/MM/yyyy HH:mm"
                Date hora = getHourFromString(strHora);
                double peso = cursor.getDouble(3);
                double diastolica = cursor.getDouble(4);
                double sistolica = cursor.getDouble(5);

                Lectura lectura = new Lectura(fecha,hora,peso,diastolica,sistolica);
                lectura.setCodigo(codigo);
                lecturas.add(lectura);

            }
        }

        return lecturas;
    }


    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertData(String fecha, String hora, double peso, double diastolica, double sistolica){

        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva


        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, fecha);
        contentValues.put(COL_3, hora);
        contentValues.put(COL_4, peso);
        contentValues.put(COL_5, diastolica);
        contentValues.put(COL_6, sistolica);

        long resultado = db.insert(MEDICDATA_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal
        //Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados

        Log.d("DATABASE", "INSERT DATA");

        return resultado == -1 ? false : true;
    }

    //Un 'Cursor' es una tabla virtual
    /*public Cursor getAll(){

        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM " + MEDICDATA_TABLE, null);

        //Ejemplo
        // SELECT * FROM AMIGOS WHERE nombre=? AND apellido LIKE '?%';
        // String[] = {"Adolfo","D"};

        Log.d("DATABASE", "CURSOR GETALL()");

        return cursor;
    }*/



    //Métodos privados
    private String getStringFromDate(Date date){
        return SDF_FECHA.format(date);
    }

    private String getStringFromHour(Date date){
        return SDF_HORA.format(date);
    }

    private Date getDateFromString(String strFecha){

        Date date = null;
        try {
            date = SDF_FECHA.parse(strFecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private Date getHourFromString(String strFecha){

        Date date = null;
        try {
            date = SDF_HORA.parse(strFecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /*public List<Lectura> getBetweenDates(Date desde, Date hasta){

        List<Lectura> lecturas = getAll();

        List<Lectura> lecturasFiltradas = new ArrayList<Lectura>();

        for (Lectura lectura: lecturas){
            if (lectura.getFechaHora().after(desde) && lectura.getFechaHora().before(hasta)){
                // pal saco
                lecturasFiltradas.add(lectura);
            }
        }

        return lecturas;
    }*/
}
