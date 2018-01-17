package com.example.borja.dialegs;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private Button btnFecha, btnHora, btnColor;
    private TextView txtFecha, txtHora, txtColor;
    private RadioGroup opcionesColores;

    private DatePickerDialog dtd;
    private TimePickerDialog tpd;

    private SimpleDateFormat dateFormatter;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Necesario para el funcionamiento de DatePickerDialog y TimePickerDialog
        calendar = Calendar.getInstance();

        // Damos el formato que queremos a la fecha
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        getViews();
        setListeners();
        eligeFecha();
        eligeHora();
    }

    private void getViews() {
        btnFecha = (Button) findViewById(R.id.btnFecha);
        btnHora = (Button) findViewById(R.id.btnHora);
        btnColor = (Button) findViewById(R.id.btnColor);
        txtFecha = (TextView) findViewById(R.id.txtFecha);
        txtHora = (TextView) findViewById(R.id.txtHora);
        txtColor = (TextView) findViewById(R.id.txtColor);
        opcionesColores = (RadioGroup) findViewById(R.id.radioGroup);
    }

    private void setListeners() {
        btnFecha.setOnClickListener(this);
        btnHora.setOnClickListener(this);
        btnColor.setOnClickListener(this);
        opcionesColores.setOnCheckedChangeListener(this);
    }

    private void eligeFecha() {
        dtd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                txtFecha.setText("Fecha:" + dateFormatter.format(newDate.getTime()));
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void eligeHora() {
        tpd = new TimePickerDialog(this, R.style.datepicker, new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                txtHora.setText("Hora: " + selectedHour + ":" + selectedMinute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);//Yes 24 hour time
    }

    private void showOpcionesColor() {
        opcionesColores.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFecha:  dtd.show(); break;
            case R.id.btnHora: tpd.show(); break;
            case R.id.btnColor: showOpcionesColor(); break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        String color = ((RadioButton)findViewById(checkedId)).getText().toString();
        txtColor.setText("Color: " + color);
    }
}

