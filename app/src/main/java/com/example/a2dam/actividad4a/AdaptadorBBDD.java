package com.example.a2dam.actividad4a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 2dam on 01/12/2017.
 */

public class AdaptadorBBDD {

    // Definiciones y constantes
    private static final String NOMBRE_BASEDATOS = "mibasededatos2.db";
    private static final String TABLA_AL = "alumnos";
    private static final String TABLA_PROF = "profesores";
    private static final String TABLA_ASIG = "asignaturas";

    private static final int VERSION_BASEDATOS = 1;

    private static final String NOMBRE_AL = "nombre";
    private static final String EDAD_AL = "edad";
    private static final String CICLO_AL = "ciclo";
    private static final String CURSO_AL = "curso";
    private static final String NOTA_AL = "nota";

    private static final String CREAR_TABLA_AL = "CREATE TABLE " + TABLA_AL + " (_id integer primary key autoincrement, nombre text, edad integer, ciclo text, curso text, nota double);";
    private static final String BORRAR_TABLA_AL = "DROP TABLE IF EXISTS " + TABLA_AL + ";";

    private static final String CREAR_TABLA_ASIG = "CREATE TABLE " + TABLA_ASIG + "(_id integer primary key autoincrement, nombre text, horas integer);";
    private static final String BORRAR_TABLA_ASIG = "DROP TABLE IF EXISTS " + TABLA_ASIG + ";";

    private static final String CREAR_TABLA_PROF = "CREATE TABLE " + TABLA_PROF + " (_id integer primary key autoincrement, nombre text, edad integer, ciclo text, curso text, despacho text);";
    private static final String BORRAR_TABLA_PROF = "DROP TABLE IF EXISTS " + TABLA_PROF + ";";

    private final Context context;

    private MiBaseDeDatos mibbdd;
    private SQLiteDatabase db;

    public AdaptadorBBDD(Context c) {
        context = c;
        mibbdd = new MiBaseDeDatos(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    public void open() {
        try {
            db = mibbdd.getWritableDatabase();
        } catch (SQLiteException e) {
            db = mibbdd.getReadableDatabase();
        }
    }

    public void insertarAlumno(String nombre, int edad, String ciclo, String curso, double nota) {
        ContentValues valores = new ContentValues();
        valores.put(NOMBRE_AL, nombre);
        valores.put(EDAD_AL, edad);
        valores.put(CICLO_AL, ciclo);
        valores.put(CURSO_AL, curso);
        valores.put(NOTA_AL, nota);
        db.insert(TABLA_AL, null, valores);
    }

    public void insertarProfesor(String nombre, int edad, String ciclo, String curso, String despacho){
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("edad", edad);
        valores.put("ciclo", ciclo);
        valores.put("curso", curso);
        valores.put("despacho", despacho);
        db.insert(TABLA_PROF,null,valores);
    }

    public void insertarAsignatura(String nombre, int horas){
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("horas", horas);
        db.insert(TABLA_ASIG, null, valores);
    }

    public ArrayList<Alumno> devuelveTablaAlumnos(){
        ArrayList<Alumno> alumnos = new ArrayList<>();
        Cursor cursor = db.query(TABLA_AL,null,null,null,null,null,null,null);

        if (cursor!=null && cursor.moveToFirst()){
            do{
                Alumno a = new Alumno(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                        cursor.getString(3), cursor.getString(4), cursor.getDouble(5));
                        alumnos.add(a);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return alumnos;
    }

    public ArrayList<Profesor> devuelveTablaProfesores(){
        ArrayList<Profesor> profesores = new ArrayList<>();
        Cursor cursor = db.query(TABLA_PROF,null,null,null,null,null,null,null);
        if (cursor!=null && cursor.moveToFirst()){
            do{
                Profesor p = new Profesor(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),
                        cursor.getString(3),cursor.getString(4),cursor.getString(5));
                profesores.add(p);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return profesores;
    }

    public ArrayList<Asignatura> devuelveTablaAsignaturas(){
        ArrayList<Asignatura> asignaturas = new ArrayList<>();
        Cursor cursor = db.query(TABLA_ASIG,null,null,null,null,null,null, null);
        if (cursor!=null && cursor.moveToFirst()){
            do{
                Asignatura asignatura = new Asignatura(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
                asignaturas.add(asignatura);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return asignaturas;
    }

    public ArrayList<Alumno> devuelveEstudiantesCiclo(String ciclo){
        ArrayList<Alumno> alumnos = new ArrayList<>();
        Cursor cursor = db.query(false, TABLA_AL, null, "ciclo=?", new String[]{ciclo}, null, null, null, null);

        if (cursor!=null && cursor.moveToFirst()){
            do{
                Alumno a = new Alumno(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                        cursor.getString(3), cursor.getString(4), cursor.getDouble(5));
                alumnos.add(a);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return alumnos;
    }

    public ArrayList<Profesor> devuelveProfesoresCiclo(String ciclo){
        ArrayList<Profesor> profesores = new ArrayList<>();
        Cursor cursor = db.query(false, TABLA_PROF, null, "ciclo=?", new String[]{ciclo}, null, null, null, null);

        if (cursor!=null && cursor.moveToFirst()){
            do{
                Profesor p = new Profesor(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5));
                profesores.add(p);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return profesores;
    }

    public ArrayList<Alumno> devuelveEstudiantesCurso(int curso){
        ArrayList<Alumno> alumnos = new ArrayList<>();
        Cursor cursor = db.query(false, TABLA_AL, null, "curso=?", new String[]{curso+""}, null, null, null, null);

        if (cursor!=null && cursor.moveToFirst()){
            do{
                Alumno a = new Alumno(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                        cursor.getString(3), cursor.getString(4), cursor.getDouble(5));
                alumnos.add(a);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return alumnos;
    }

    public ArrayList<Profesor> devuelveProfesoresCurso(int curso){
        ArrayList<Profesor> profesores = new ArrayList<>();
        Cursor cursor = db.query(false, TABLA_PROF, null, "curso=?", new String[]{curso+""}, null, null, null, null);
        if (cursor!=null && cursor.moveToFirst()){
            do{
                Profesor p = new Profesor(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5));
                profesores.add(p);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return profesores;
    }

    public ArrayList<Alumno> devuelveEstudiantesCicloCurso(String ciclo, int curso){
        ArrayList<Alumno> alumnos = new ArrayList<>();
        Cursor cursor = db.query(false, TABLA_AL, null, "ciclo=? and curso=?", new String[]{ciclo, curso+""}, null, null, null, null);
        if (cursor!=null && cursor.moveToFirst()){
            do{
                Alumno a = new Alumno(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                        cursor.getString(3), cursor.getString(4), cursor.getDouble(5));
                alumnos.add(a);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return alumnos;
    }

    public ArrayList<Profesor> devuelveProfesoresCicloCurso(String ciclo, int curso){
        ArrayList<Profesor> profesores = new ArrayList<>();
        Cursor cursor = db.query(false, TABLA_PROF, null, "ciclo=? and curso=?", new String[]{ciclo, curso+""}, null, null, null, null);
        if (cursor!=null && cursor.moveToFirst()){
            do{
                Profesor p = new Profesor(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5));
                profesores.add(p);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return profesores;
    }


    public void borrarRegistroAlumno(int id){
        db.delete(TABLA_AL,"_id="+id, null);
    }

    public void borrarRegistroProfesor(int id){
        db.delete(TABLA_PROF,"_id="+id, null);
    }

    public void vaciarTablas(){
        db.delete(TABLA_AL,null,null);
        db.delete(TABLA_PROF,null,null);
    }

    /* M'agradaria saber si te algun sentit borrar la base de dades aci */

    public void borrarBaseDatos(){
        if (checkDataBase())
            context.deleteDatabase(NOMBRE_BASEDATOS);
    }

    /**
     * Comprova que existeix la base de dades i es pot llegir
     *
     * torna true si existeix i es pot llegir, false en cas contrari
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = db.openDatabase("/data/data/com.example.a2dam.actividad4a/databases/"+NOMBRE_BASEDATOS, null,
                    db.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            // la base de dades no existeix
        }
        return checkDB != null;
    }


    private static class MiBaseDeDatos extends SQLiteOpenHelper {

        // CONSTRUCTOR de la clase
        public MiBaseDeDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREAR_TABLA_AL);
            db.execSQL(CREAR_TABLA_PROF);
            db.execSQL(CREAR_TABLA_ASIG);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(BORRAR_TABLA_AL);
            db.execSQL(BORRAR_TABLA_PROF);
            db.execSQL(BORRAR_TABLA_ASIG);
            onCreate(db);
        }
    }
}
