package com.quiz.mmaguero.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by mmaguero on 03/02/2017.
 */

public class MenuActividad extends Activity implements View.OnClickListener {

    private Button btnJugar, btnResultados, btnOtros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_menu);

        this.btnJugar = (Button) this.findViewById(R.id.btnJugar_);
        this.btnResultados = (Button) this.findViewById(R.id.btnResultados);
        this.btnOtros = (Button) this.findViewById(R.id.btnOtros);
        //
        this.btnJugar.setOnClickListener(this);
        this.btnResultados.setOnClickListener(this);
        this.btnOtros.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnJugar_) {
            this.startActivity(new Intent(MenuActividad.this, ActividadPrincipal.class));
        } else if (view.getId() == R.id.btnResultados) {
            this.startActivity(new Intent(MenuActividad.this, Resultados.class));
        } else if (view.getId() == R.id.btnOtros) {
            this.startActivity(new Intent(MenuActividad.this, Presentacion.class));
        }
    }
}
