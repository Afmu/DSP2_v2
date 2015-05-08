package com.softbwh.jesus.dsp2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by afmu on 4/5/15.
 */
public class PYRDataSource {

    //Factorias de enunciados
    private FabricaEnunciadoTexto fTexto;
    private FabricaEnunciadoAudio fAudio;
    private FabricaEnunciadoGrafico fGrafica;

    //Metainformación de la base de datos
    public static final String PREGUNTAS_TABLE_NAME = "Preguntas";
    public static final String RESPUESTAS_TABLE_NAME = "Respuestas";
    public static final String CATEGORIAS_TABLE_NAME = "Categorias";
    public static final String TIPOS_TABLE_NAME = "Tipos";
    public static final String CLASES_TABLE_NAME = "Clases";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";

    //Campos de la tabla Preguntas
    public static class ColumnPreguntas{
        public static final String ID_PREGUNTAS = "preguntas"+BaseColumns._ID;
        public static final String ID_RESPUESTA = ColumnRespuestas.ID_RESPUESTAS;
        public static final String CONTENIDO_PREGUNTAS = "contenido";
        public static final String DESCRIPCION_PREGUNTAS = "descripcion";
        public static final String CATEGORIA_PREGUNTAS = ColumnCategorias.ID_CATEGORIAS;
        public static final String TIPO_PREGUNTAS = ColumnTipos.ID_TIPOS;
        public static final String CLASE_PREGUNTAS = ColumnClases.ID_CLASES;
    }

    //Campos de la tabla Respuestas
    public static class ColumnRespuestas{
        public static final String ID_RESPUESTAS = "respuestas"+BaseColumns._ID;
        public static final String CONTENIDO_RESPUESTAS = "contenido";
        public static final String DESCRIPCION_RESPUESTAS = "descripcion";
        public static final String TIPO_RESPUESTAS = ColumnTipos.ID_TIPOS;
        public static final String CLASE_RESPUESTAS = ColumnClases.ID_CLASES;
    }

    //Campos de la tabla Categorias
    public static class ColumnCategorias{
        public static final String ID_CATEGORIAS = "categorias"+BaseColumns._ID;
        public static final String CONTENIDO_CATEGORIAS = "contenido";
    }

    //Campos de la tabla Tipos
    public static class ColumnTipos{
        public static final String ID_TIPOS = "tipos"+BaseColumns._ID;
        public static final String CONTENIDO_TIPOS = "contenido";
    }

    //Campos de la tabla Clases
    public static class ColumnClases{
        public static final String ID_CLASES = "clases"+BaseColumns._ID;
        public static final String CONTENIDO_CLASES = "contenido";
    }

    //Script de Creación de la tabla Preguntas
    public static final String CREATE_PREGUNTAS_SCRIPT =
            "create table "+PREGUNTAS_TABLE_NAME+"(" +
                    ColumnPreguntas.ID_PREGUNTAS+" "+INT_TYPE+" primary key autoincrement," +
                    ColumnPreguntas.CONTENIDO_PREGUNTAS+" "+STRING_TYPE+" not null," +
                    ColumnPreguntas.DESCRIPCION_PREGUNTAS+" "+STRING_TYPE+" not null," +
                    ColumnPreguntas.ID_PREGUNTAS+" "+INT_TYPE+" not null," +
                    ColumnPreguntas.CATEGORIA_PREGUNTAS+" "+INT_TYPE+" not null," +
                    ColumnPreguntas.TIPO_PREGUNTAS+" "+INT_TYPE+" not null," +
                    ColumnPreguntas.CLASE_PREGUNTAS+" "+INT_TYPE+ "not null)";

    //Script de Creación de la tabla Respuestas
    public static final String CREATE_RESPUESTAS_SCRIPT =
            "create table "+RESPUESTAS_TABLE_NAME+"(" +
                    ColumnRespuestas.ID_RESPUESTAS+" "+INT_TYPE+" primary key autoincrement," +
                    ColumnRespuestas.CONTENIDO_RESPUESTAS+" "+STRING_TYPE+" not null," +
                    ColumnRespuestas.DESCRIPCION_RESPUESTAS+" "+STRING_TYPE+" not null," +
                    ColumnRespuestas.TIPO_RESPUESTAS+" "+INT_TYPE+" not null, "+
                    ColumnRespuestas.CLASE_RESPUESTAS+" "+INT_TYPE+ "not null)";

    //Script de Creación de la tabla Categorias
    public static final String CREATE_CATEGORIA_SCRIPT =
            "create table "+CATEGORIAS_TABLE_NAME+"(" +
                    ColumnCategorias.ID_CATEGORIAS+" "+INT_TYPE+" primary key autoincrement," +
                    ColumnCategorias.CONTENIDO_CATEGORIAS+" "+STRING_TYPE+" not null)";

    //Script de Creación de la tabla Tipos
    public static final String CREATE_TIPOS_SCRIPT =
            "create table "+TIPOS_TABLE_NAME+"(" +
                    ColumnTipos.ID_TIPOS+" "+INT_TYPE+" primary key autoincrement," +
                    ColumnTipos.CONTENIDO_TIPOS+" "+STRING_TYPE+" not null)";

    //Script de Creación de la tabla Clases
    public static final String CREATE_CLASES_SCRIPT =
            "create table "+CLASES_TABLE_NAME+"(" +
                    ColumnClases.ID_CLASES+" "+INT_TYPE+" primary key autoincrement," +
                    ColumnClases.CONTENIDO_CLASES+" "+STRING_TYPE+" not null)";

    //Scripts de inserción por defecto
    public static final String INSERT_PREGUNTAS_SCRIPT =
            "INSERT INTO "+PREGUNTAS_TABLE_NAME+" VALUES" +
                    "(1,'¿Cuántas Champions Leagues posee el Real Madrid?','',1,1,1,1)," +
                    "(2,'¿Qué club ganó La Liga BBVA en 2014?','',4,1,2,1)," +
                    "(3,'¿Qué club ganó la Copa del Rey de Fúbol en 2014?','',2,1,2,1)";

    public static final String INSERT_RESPUESTAS_SCRIPT =
            "INSERT INTO "+RESPUESTAS_TABLE_NAME+" VALUES" +
                    "(1,'10','',1,1)," +
                    "(2,'Real Madrid','',2,1)," +
                    "(3,'FC Barcelona','',2,1)," +
                    "(4,'Atlético de Madrid,'',2,1)";

    public static final String INSERT_CATEGORIAS_SCRIPT =
            "INSERT INTO "+CATEGORIAS_TABLE_NAME+" VALUES" +
                    "(1, 'futbol')";

    public static final String INSERT_TIPOS_SCRIPT =
            "INSERT INTO "+TIPOS_TABLE_NAME+" VALUES" +
                    "(1, 'numero'), " +
                    "(2, 'club')";

    public static final String INSERT_CLASES_SCRIPT =
            "INSERT INTO "+CLASES_TABLE_NAME+" VALUES" +
                    "(1, 'texto'), " +
                    "(2, 'audio'), " +
                    "(3, 'grafica')";

    private PYRReaderDBHelper openHelper;
    private SQLiteDatabase database;

    public PYRDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        openHelper = new PYRReaderDBHelper(context);
        database = openHelper.getReadableDatabase();
    }

    public ArrayList<Pregunta> obtenerPreguntas(String categoria){
        ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
        String columns[] = new String[]{ColumnPreguntas.CONTENIDO_PREGUNTAS, ColumnPreguntas.DESCRIPCION_PREGUNTAS, ColumnPreguntas.ID_RESPUESTA, ColumnPreguntas.CLASE_PREGUNTAS};
        String selection = ColumnPreguntas.CATEGORIA_PREGUNTAS + " = ? ";//WHERE categoria = ?
        String selectionArgs[] = new String[]{categoria};
        Cursor c = database.query(PREGUNTAS_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        while(c.moveToNext()) {
            String p = c.getString(c.getColumnIndex(ColumnPreguntas.CONTENIDO_PREGUNTAS));
            String d = c.getString(c.getColumnIndex(ColumnPreguntas.DESCRIPCION_PREGUNTAS));
            String t = c.getString(c.getColumnIndex(ColumnPreguntas.TIPO_PREGUNTAS));
            int id_r = c.getInt(c.getColumnIndex(ColumnPreguntas.ID_RESPUESTA));
            String columns_r[] = new String[]{ColumnRespuestas.CONTENIDO_RESPUESTAS, ColumnRespuestas.DESCRIPCION_RESPUESTAS};
            String selection_r = ColumnRespuestas.ID_RESPUESTAS + " = ? ";//WHERE id_respuesta = ?
            String selectionArgs_r[] = new String[]{String.valueOf(id_r)};
            Cursor c_r = database.query(RESPUESTAS_TABLE_NAME, columns_r, selection_r, selectionArgs_r, null, null, null);
            String clase = c.getString(c.getColumnIndex(ColumnPreguntas.CLASE_PREGUNTAS));
            while (c_r.moveToNext()) {
                String cont_r = c_r.getString(c_r.getColumnIndex(ColumnRespuestas.CONTENIDO_RESPUESTAS));
                String desc_r = c_r.getString(c_r.getColumnIndex(ColumnRespuestas.DESCRIPCION_RESPUESTAS));
                if (clase == "texto")
                    preguntas.add(new PreguntaTexto(p, new RespuestaTexto(cont_r),t));
                else if (clase == "audio")
                    preguntas.add(new PreguntaAudio(p, new RespuestaAudio(cont_r, desc_r),d,t));
                else if (clase == "grafica")
                    preguntas.add(new PreguntaGrafica(p, new RespuestaGrafica(cont_r, desc_r),d,t));
                else {
                    System.out.println("Error en la clase de las preguntas");
                    return null;
                }
            }
        }
        return preguntas;
    }

    public ArrayList<Respuesta> obtenerRespuestas(String tipo, String clase){
        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        String columns[] = new String[]{ColumnRespuestas.CONTENIDO_RESPUESTAS, ColumnRespuestas.CLASE_RESPUESTAS, ColumnRespuestas.DESCRIPCION_RESPUESTAS};
        String selection = ColumnRespuestas.TIPO_RESPUESTAS + " = ? AND " + ColumnRespuestas.CLASE_RESPUESTAS + " = ? ";//WHERE tipo = ? AND clse = ?
        String selectionArgs[] = new String[]{tipo, clase};
        Cursor c = database.query(RESPUESTAS_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        String r = c.getString(c.getColumnIndex(ColumnRespuestas.CONTENIDO_RESPUESTAS));
        String d = c.getString(c.getColumnIndex(ColumnRespuestas.DESCRIPCION_RESPUESTAS));
        while(c.moveToNext()) {
            if (clase == "texto")
                respuestas.add(new RespuestaTexto(r));
            else if (clase == "audio")
                respuestas.add(new RespuestaAudio(r, d));
            else if (clase == "grafica")
                respuestas.add(new RespuestaGrafica(r, d));
            else {
                System.out.println("Error en la clase de las respuestas");
                return null;
            }
        }
        return respuestas;
    }

    public ArrayList<Enunciado> obtenerEnunciados(String cat){
        ArrayList<Enunciado> enunciados = new ArrayList<Enunciado>();
        ArrayList<Pregunta> preguntas = obtenerPreguntas(cat);
        Collections.shuffle(preguntas, new Random(System.nanoTime()));
        for(int i=0; i<10; i++){
            Pregunta p = preguntas.get(i);
            String clase = "";
            if(p instanceof PreguntaTexto)
                clase = "texto";
            else if(p instanceof PreguntaAudio)
                clase = "audio";
            else if(p instanceof PreguntaGrafica)
                clase = "grafica";
            else {
                System.out.println("Error en la clase de la respuesta");
                return null;
            }
            ArrayList<Respuesta> respuestas = obtenerRespuestas(p.getTipo(), clase);
            Collections.shuffle(respuestas, new Random(System.nanoTime()));
            ArrayList<Respuesta> r = new ArrayList<Respuesta>();
            r.add(respuestas.get(0));
            r.add(respuestas.get(1));
            r.add(respuestas.get(2));
            if(p instanceof PreguntaTexto)
                enunciados.add(fTexto.crearEnunciado(p, r));
            else if(p instanceof PreguntaAudio)
                enunciados.add(fAudio.crearEnunciado(p, r));
            else if(p instanceof PreguntaGrafica)
                enunciados.add(fGrafica.crearEnunciado(p, r));
            else {
                System.out.println("Error en la creacion del enunciado");
                return null;
            }
        }
        Collections.shuffle(enunciados, new Random(System.nanoTime()));
        return enunciados;
    }
}
