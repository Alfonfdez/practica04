package com.afr.sqlitehelloworld;

import android.content.ContentValues;
import android.content.Context;
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

    public static final String DATABASE_NAME = "amigos.db";

    public static final String AMIGOS_TABLE = "AMIGOS";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "APELLIDO1";
    public static final String COL_4 = "APELLIDO2";

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

        strSQL.append("CREATE TABLE").append(AMIGOS_TABLE).append(" ").append(" (")
                .append(COL_1).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COL_2).append(" TEXT NOT NULL, ")
                .append(COL_3).append(" TEXT NOT NULL, ")
                .append(COL_4).append(" TEXT")
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
        db.execSQL("DROP TABLE IF EXISTS " + AMIGOS_TABLE);
        onCreate(db);
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertData(String nombre, String apellido1, String apellido2){

        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva


        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, nombre);
        contentValues.put(COL_3, apellido1);
        contentValues.put(COL_4, apellido2);

        long resultado = db.insert(AMIGOS_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal
        //Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados

        return resultado == -1 ? false : true;
    }
}
