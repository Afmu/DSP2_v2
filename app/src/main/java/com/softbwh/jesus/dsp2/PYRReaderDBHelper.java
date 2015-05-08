package com.softbwh.jesus.dsp2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by afmu on 4/5/15.
 */
public class PYRReaderDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PYR.db";
    public static final int DATABASE_VERSION = 1;

    public PYRReaderDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear las tablas
        db.execSQL(PYRDataSource.CREATE_CATEGORIA_SCRIPT);
        db.execSQL(PYRDataSource.CREATE_TIPOS_SCRIPT);
        db.execSQL(PYRDataSource.CREATE_CLASES_SCRIPT);
        db.execSQL(PYRDataSource.CREATE_PREGUNTAS_SCRIPT);
        db.execSQL(PYRDataSource.CREATE_RESPUESTAS_SCRIPT);
        //Insertar registros iniciales
        db.execSQL(PYRDataSource.INSERT_CATEGORIAS_SCRIPT);
        db.execSQL(PYRDataSource.INSERT_TIPOS_SCRIPT);
        db.execSQL(PYRDataSource.INSERT_CLASES_SCRIPT);
        db.execSQL(PYRDataSource.INSERT_PREGUNTAS_SCRIPT);
        db.execSQL(PYRDataSource.INSERT_RESPUESTAS_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Añade los cambios que se realizarán en el esquema
    }
}
