package com.quiz.mmaguero.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by mmaguero on 03/02/2017.
 */

public class Presentacion extends Activity implements View.OnClickListener {
    private Button btnVolver, btnJuego, btnQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentacion);

        this.btnVolver = (Button) this.findViewById(R.id.btnVolver_);
        this.btnJuego = (Button) this.findViewById(R.id.btnJuego);
        this.btnQuiz = (Button) this.findViewById(R.id.btnQuiz);
        //
        this.btnVolver.setOnClickListener(this);
        this.btnJuego.setOnClickListener(this);
        this.btnQuiz.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnVolver_) {
            this.startActivity(new Intent(Presentacion.this, MenuActividad.class));
        } else if (view.getId() == R.id.btnJuego) {
            Intent webLink = new Intent(android.content.Intent.ACTION_VIEW);
            webLink.setData(Uri.parse(
                    "http://www.quiz.es/juego-online/"));
            startActivity(webLink);
        } else if (view.getId() == R.id.btnQuiz) {
            Intent webLink = new Intent(android.content.Intent.ACTION_VIEW);
            webLink.setData(Uri.parse(
                    "http://www.upsocl.com/cat/quiz/"));
            startActivity(webLink);
        }
    }
}
