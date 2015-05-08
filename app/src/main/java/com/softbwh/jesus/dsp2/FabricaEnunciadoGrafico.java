package com.softbwh.jesus.dsp2;

import java.util.ArrayList;

/**
 * Created by jesus on 28/04/15.
 */
public class FabricaEnunciadoGrafico extends FabricaEnunciado {

    public Enunciado crearEnunciadoGrafico(PreguntaGrafica p, ArrayList<Respuesta> r){
        Enunciado e=new EnunciadoGrafico(p,r);

        return e;
    }

    @Override
    public Enunciado crearEnunciado(Pregunta p, ArrayList<Respuesta> r) {
        return null;
    }
}
