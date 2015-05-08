package com.softbwh.jesus.dsp2;

/**
 * Created by jesus on 28/04/15.
 */
public abstract class Pregunta {

    private String contenido;
    private Respuesta respuesta;
    private String descripcion;
    private String tipo;

    public Pregunta(String p, Respuesta r, String des, String tip){
        contenido=p;
        respuesta=r;
        descripcion=des;
        tipo=tip;
    }

    public String getContenido(){
        return contenido;
    }

    public String getRespuesta(){
        return respuesta.getContenidoRespuesta();
    }

    public String getDescripcion(){
        return descripcion;
    }

    public String getTipo(){
        return tipo;
    }
}
