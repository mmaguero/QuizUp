package com.quiz.mmaguero.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mmaguero on 03/02/2017.
 */

public class Despedida extends Activity implements View.OnClickListener {
    private Utilidad utilidad;
    private MediaPlayer sonGanador;
    private TextView txtDespedida;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.despedida);

        this.btnVolver = (Button) this.findViewById(R.id.btnVolver_);
        this.utilidad = (Utilidad) getApplicationContext();
        this.txtDespedida = (TextView) this.findViewById(R.id.txtDespedida);
        this.txtDespedida.setText("Has conseguido " + this.utilidad.getPuntuacion() +
                " de puntuación. \n\n¿Y si continuas jugando?");
        this.sonGanador = MediaPlayer.create(this, R.raw.son_ganador);
        this.sonGanador.start();
        this.btnVolver.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnVolver_) {
            this.startActivity(new Intent(Despedida.this, MenuActividad.class));
        }
    }
}
