package com.softbwh.jesus.dsp2;

import java.util.ArrayList;

/**
 * Created by jesus on 28/04/15.
 */
public abstract class FabricaEnunciado {

    public abstract Enunciado crearEnunciado(Pregunta p, ArrayList<Respuesta> r);
}
