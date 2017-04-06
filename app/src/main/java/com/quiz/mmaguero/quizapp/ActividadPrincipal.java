package com.quiz.mmaguero.quizapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by mmaguero on 03/02/2017.
 */

public class ActividadPrincipal extends Activity implements View.OnClickListener {

    public final static int NUM_PREGUNTA = 10;
    private final static String COL_BOTON = "#778899";

    private int puntuacion;
    private Utilidad utilidad;
    private DBPref mgtDB;
    private Stack lisPreguntas = new Stack();
    private Pregunta pregunta;
    private TextView preInicial;
    private ImageView imagen;
    private Button opc1, opc2, opc3, opc4;
    private LinearLayout pnlAudio;
    private MediaPlayer sonPregunta, sonAcierto, sonError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        this.utilidad = (Utilidad) this.getApplicationContext();
        this.puntuacion = 0;

        this.preInicial = (TextView) this.findViewById(R.id.txtPreInicial_);
        this.imagen = (ImageView) this.findViewById(R.id.imgPregunta);
        this.opc1 = (Button) this.findViewById(R.id.btnOpcion1);
        this.opc2 = (Button) this.findViewById(R.id.btnOpcion2);
        this.opc3 = (Button) this.findViewById(R.id.btnOpcion3);
        this.opc4 = (Button) this.findViewById(R.id.btnOpcion4);
        this.pnlAudio = (LinearLayout) findViewById(R.id.lytAudio);

        this.mgtDB = new DBPref(this);

        this.sonAcierto = MediaPlayer.create(this, R.raw.son_acierto);
        this.sonError = MediaPlayer.create(this, R.raw.son_error);

        Cursor preguntas = this.mgtDB.getPreguntas(DBPref.Categoria.CINE, DBPref.Dificultad.FACIL, ActividadPrincipal.NUM_PREGUNTA);

        if (preguntas.moveToFirst()) {
            do {
                String pregunta = preguntas.getString(preguntas.getColumnIndex("pregunta"));
                String imagen = preguntas.getString(preguntas.getColumnIndex("imagen"));
                String sonido = preguntas.getString(preguntas.getColumnIndex("sonido"));
                String rtaCorrecta = preguntas.getString(preguntas.getColumnIndex("rtaCorrecta"));
                int tipo = preguntas.getInt(preguntas.getColumnIndex("tipo"));

                ArrayList<String> rtaIncorrectas = new ArrayList();
                rtaIncorrectas.add(preguntas.getString(preguntas.getColumnIndex("rtaIncorrecta1")));
                rtaIncorrectas.add(preguntas.getString(preguntas.getColumnIndex("rtaIncorrecta2")));
                rtaIncorrectas.add(preguntas.getString(preguntas.getColumnIndex("rtaIncorrecta3")));

                this.lisPreguntas.push(new Pregunta(pregunta, tipo, imagen, sonido, rtaCorrecta, rtaIncorrectas));
            } while (preguntas.moveToNext());
        }

        this.mgtDB.close();

        this.setPregunta((Pregunta) this.lisPreguntas.pop());

        this.opc1.setOnClickListener(this);
        this.opc2.setOnClickListener(this);
        this.opc3.setOnClickListener(this);
        this.opc4.setOnClickListener(this);
    }

    private void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;

        ArrayList<String> respuestas = new ArrayList<String>(pregunta.getRespuestas());

        this.preInicial.setText(pregunta.getPregunta());

        this.opc1.setText(respuestas.get(0));
        this.opc2.setText(respuestas.get(1));
        this.opc3.setText(respuestas.get(2));
        this.opc4.setText(respuestas.get(3));

        if (this.pregunta.getTipo() == 1) {
            this.imagen.setVisibility(View.GONE);
            this.imagen.setBackgroundResource(0);
            this.pnlAudio.setVisibility(View.GONE);

            this.modColTamTexto(true, 20);

        } else if (this.pregunta.getTipo() == 2) {
            this.imagen.setVisibility(View.VISIBLE);
            this.imagen.setBackgroundResource(getResources().getIdentifier(this.pregunta.getImagen(), "drawable", getPackageName()));
            this.pnlAudio.setVisibility(View.GONE);

            this.modColTamTexto(true, 20);

        } else if (this.pregunta.getTipo() == 3) {
            this.imagen.setVisibility(View.GONE);
            this.imagen.setBackgroundResource(0);
            this.pnlAudio.setVisibility(View.GONE);

            this.modColTamTexto(false, 0);

            this.opc1.setBackgroundResource(getResources().getIdentifier(respuestas.get(0), "drawable", getPackageName()));
            this.opc2.setBackgroundResource(getResources().getIdentifier(respuestas.get(1), "drawable", getPackageName()));
            this.opc3.setBackgroundResource(getResources().getIdentifier(respuestas.get(2), "drawable", getPackageName()));
            this.opc4.setBackgroundResource(getResources().getIdentifier(respuestas.get(3), "drawable", getPackageName()));

        } else if (this.pregunta.getTipo() == 4) {
            this.imagen.setVisibility(View.GONE);
            this.imagen.setBackgroundResource(0);
            this.pnlAudio.setVisibility(View.VISIBLE);
            this.sonPregunta = MediaPlayer.create(this, Uri.parse("android.resource://com.quiz.mmaguero.quizapp/raw/" + this.pregunta.getSonido()));

            this.modColTamTexto(true, 20);
        }
    }

    @Override
    public void onClick(View view) {
        Button seleccionado = (Button) view;

        if (this.sonPregunta != null) {
            this.pause(view);
        }

        if (seleccionado.getText().toString().equals(this.pregunta.getRespuesta())) {
            this.puntuacion++;

            Iterator iterator = this.lisPreguntas.iterator();
            if (iterator.hasNext()) {
                Toast.makeText(this, "¡CORRECTO!", Toast.LENGTH_SHORT).show();
                this.sonAcierto.start();
                this.setPregunta((Pregunta) this.lisPreguntas.pop());
            } else {
                this.utilidad.setPuntuacion(this.puntuacion);
                this.startActivity(new Intent(ActividadPrincipal.this, Despedida.class));
            }
        } else {
            this.sonError.start();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setMessage("Te equivocaste :(, puedes volver a intentarlo pero te restará otro punto...")
                    .setPositiveButton("Seguir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            puntuacion -= 2;
                        }
                    })
                    .setNegativeButton("Empezar de vuelta", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            utilidad.setPuntuacion(puntuacion);
                            mostrarRespuesta();
                            finish();
                        }
                    })
                    .show();
        }
    }

    public void mostrarRespuesta() {
        Toast.makeText(this, "Respuesta Correcta: " + this.pregunta.getRespuesta(), Toast.LENGTH_SHORT).show();
    }

    public void play(View view) {
        this.sonPregunta.start();
    }

    public void pause(View view) {
        this.sonPregunta.pause();
    }

    public void modColTamTexto(boolean color, int tamanho) {
        if (color) {
            this.opc1.setBackgroundColor(Color.parseColor(COL_BOTON));
            this.opc2.setBackgroundColor(Color.parseColor(COL_BOTON));
            this.opc3.setBackgroundColor(Color.parseColor(COL_BOTON));
            this.opc4.setBackgroundColor(Color.parseColor(COL_BOTON));
        }
        //
        this.opc1.setTextSize(tamanho);
        this.opc2.setTextSize(tamanho);
        this.opc3.setTextSize(tamanho);
        this.opc4.setTextSize(tamanho);
    }

}
