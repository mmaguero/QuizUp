package com.quiz.mmaguero.quizapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by mmaguero on 03/02/2017.
 */

public class Pregunta {
    private int tipo;
    private String pregunta, imagen, sonido, respuesta;
    private ArrayList<String> rtaIncorrectas, respuestas;

    public Pregunta(String pregunta, int tipo, String imagen, String sonido, String respuesta, ArrayList<String> rtaIncorrectas) {
        this.pregunta = pregunta;
        this.tipo = tipo;
        this.imagen = imagen;
        this.sonido = sonido;
        this.respuesta = respuesta;
        this.rtaIncorrectas = new ArrayList<String>(rtaIncorrectas);
        this.respuestas = new ArrayList();
        this.respuestas.add(respuesta);

        Iterator iterator = this.rtaIncorrectas.iterator();
        while (iterator.hasNext()) {
            Object elemento = iterator.next();
            this.respuestas.add((String) elemento);
        }
        Collections.shuffle(this.respuestas);
    }

    public String getPregunta() {
        return this.pregunta;
    }

    public int getTipo() {
        return this.tipo;
    }

    public String getImagen() {
        return this.imagen;
    }

    public String getSonido() {
        return this.sonido;
    }

    public String getRespuesta() {
        return this.respuesta;
    }

    public ArrayList<String> getRespuestas() {
        return this.respuestas;
    }
}
