package com.unir.app.eventos.plataf.sw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView textViewTipo, textViewNombre, textViewFechaInicio, textViewFechaFin, textViewHoraInicio, textViewHoraFin;
    Button buttonAceptar;
    ImageView imageViewFinEvento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //Init UI Components
        textViewTipo = findViewById(R.id.textViewTipoEvento);
        textViewNombre = findViewById(R.id.textViewNombreEvento);
        textViewFechaInicio = findViewById(R.id.textViewFechaInicioEvento);
        textViewFechaFin = findViewById(R.id.textViewFechaFinEvento);
        buttonAceptar = findViewById(R.id.buttonAceptar);
        imageViewFinEvento = findViewById(R.id.imageViewFinEvento);

        buttonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoPrincipalView();
            }
        });

        //Get data from previous screen
        populateEventData();

        //Change activity title
        this.setTitle("Detalle");

    }

    public void populateEventData(){
        String tipoEvento = getIntent().getStringExtra("tipoEvento");
        String nombreEvento = getIntent().getStringExtra("nombreEvento");
        String descripcionEvento = getIntent().getStringExtra("descripcionEvento");
        String fechaInicio = getIntent().getStringExtra("fechaInicio");
        boolean isAllDayEvent = getIntent().getBooleanExtra("switchAllDayEvent", false);
        if(!isAllDayEvent){
            String horaInicio = getIntent().getStringExtra("horaInicio");
            String fechaFin = getIntent().getStringExtra("fechaFin");
            String horaFin = getIntent().getStringExtra("horaFin");
            textViewFechaInicio.setText(fechaInicio + "  "+  horaInicio);
            textViewFechaFin.setText(fechaFin + "  " + horaFin);

        }else{
            textViewFechaInicio.setText(fechaInicio + " Todo el día");
            textViewFechaFin.setText("");
            imageViewFinEvento.setVisibility(View.GONE);

        }


        textViewTipo.setText(tipoEvento);
        textViewNombre.setText(nombreEvento+ ": "+ descripcionEvento);


    }
    public void gotoPrincipalView(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        this.finish();
    }
}