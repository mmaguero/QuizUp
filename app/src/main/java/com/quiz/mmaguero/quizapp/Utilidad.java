package com.quiz.mmaguero.quizapp;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by mmaguero on 03/02/2017.
 */

public class Utilidad extends Application {
    private int puntuacion = 0;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public int getPuntuacion() {
        return this.puntuacion;
    }

    void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
