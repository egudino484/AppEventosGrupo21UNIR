package com.unir.app.eventos.plataf.sw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerEvent;
    EditText dateInit;
    EditText dateEnd;

    private int lastYear, lastMonth, lastDayMonthYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerEvent = findViewById(R.id.idSpiner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.eventType, android.R.layout.simple_spinner_item);
        spinnerEvent.setAdapter(adapter);

        dateInit = findViewById(R.id.dateInit);
        dateEnd = findViewById(R.id.dateEnd);
        final Calendar calendar = Calendar.getInstance();
        lastYear = calendar.get(Calendar.YEAR);
        lastMonth = calendar.get(Calendar.MONTH);
        lastDayMonthYear = calendar.get(Calendar.DAY_OF_MONTH);

        refreshDateText(dateInit);
        refreshDateText(dateEnd);

        dateInit.setOnClickListener(v -> {
            DatePickerDialog dialogDateInit = new DatePickerDialog(MainActivity.this, listenerInitDatePicker, lastYear, lastMonth, lastDayMonthYear);
            dialogDateInit.show();
        });

        dateEnd.setOnClickListener(v -> {
            DatePickerDialog dialogDateEnd = new DatePickerDialog(MainActivity.this, listenerEndDatePicker, lastYear, lastMonth, lastDayMonthYear);
            dialogDateEnd.show();
        });


    }

    private final DatePickerDialog.OnDateSetListener listenerInitDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            lastYear = year;
            lastMonth = month;
            lastDayMonthYear = day;
            refreshDateText(dateInit);

        }
    };

    private final DatePickerDialog.OnDateSetListener listenerEndDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            lastYear = year;
            lastMonth = month;
            lastDayMonthYear = day;
            refreshDateText(dateEnd);

        }
    };

    public void refreshDateText(EditText editText) {
        String date = String.format(Locale.getDefault(), "%02d-%02d-%02d", lastYear, lastMonth+1, lastDayMonthYear);
        editText.setText(date);
    }

}