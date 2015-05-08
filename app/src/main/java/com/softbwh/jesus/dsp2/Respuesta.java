package com.softbwh.jesus.dsp2;

/**
 * Created by jesus on 28/04/15.
 */
public abstract class Respuesta {

    private String respuesta;

    public Respuesta(String r){
        respuesta=r;
    }

    public String getContenidoRespuesta(){
        return respuesta;
    }
}
