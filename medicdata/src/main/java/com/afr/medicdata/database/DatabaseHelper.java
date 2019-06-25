package com.afr.medicdata.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    //Uno de los 3 Constructores del Constructor padre (SQLiteOpenHelper)
    /*public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }*/

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
                .append(COL_2).append(" TEXT, ")
                .append(COL_3).append(" TEXT, ")
                .append(COL_4).append(" REAL, ")
                .append(COL_5).append(" REAL, ")
                .append(COL_6).append(" REAL")
                .append(")");

        Log.d("DATABASE", "TERCERO - ONCREATE");
        Log.d("DATABASE", strSQL.toString());

        db.execSQL(strSQL.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Entramos aquí cuando se detecta un cambio de versión en la base de datos
        //Normalmente esto conlleva la creación de nuevas tablas o columnas en tablas ya existentes
        //DROP TABLE elimina la tabla y "onCreate" vuelve a crear el esquema desde cero
        db.execSQL("DROP TABLE IF EXISTS " + MEDICDATA_TABLE);
        onCreate(db);
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

        return resultado == -1 ? false : true;
    }

    //Un 'Cursor' es una tabla virtual
    public Cursor getAll(){

        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM " + MEDICDATA_TABLE, null);

        //Ejemplo
        // SELECT * FROM AMIGOS WHERE nombre=? AND apellido LIKE '?%';
        // String[] = {"Adolfo","D"};
        return cursor;
    }
}
