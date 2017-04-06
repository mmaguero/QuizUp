package com.quiz.mmaguero.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mmaguero on 03/02/2017.
 */

public class Resultados extends Activity implements View.OnClickListener {
    private Utilidad utilidad;
    private TextView txtResultados;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados);

        this.btnVolver = (Button) this.findViewById(R.id._btnVolver);
        this.utilidad = (Utilidad) getApplicationContext();
        this.txtResultados = (TextView) this.findViewById(R.id.txtResultados);
        this.txtResultados.setText(this.txtResultados.getText() + " " + Integer.toString(this.utilidad.getPuntuacion()));
        this.btnVolver.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id._btnVolver) {
            this.startActivity(new Intent(Resultados.this, MenuActividad.class));
        }
    }
}
