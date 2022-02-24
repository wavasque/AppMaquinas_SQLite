package com.example.appmaquinas_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appmaquinas_sqlite.adaptores.ListaMaquinasAdapter;
import com.example.appmaquinas_sqlite.db.DbMaquinas;
import com.example.appmaquinas_sqlite.entidades.EMaquinas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class NuevoMaquinaActivity extends AppCompatActivity {

    EditText fec_pro;
    EditText fec_ult;
    DatePickerDialog.OnDateSetListener onDateSetListener,onDateSetListener2;
    final Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    Spinner spinner,spinner2 ;
    FloatingActionButton save ;
    EditText maquina,ubicacion ;


    //iniciar Lista
    RecyclerView listaMaquinas ;
    ArrayList<EMaquinas> listaArrayMaquinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_maquina);

        //https://www.youtube.com/watch?v=XG8OpQ7X-nY
        //Llenar recycleview
        llenarlista(0 , "todos");

        fec_pro = findViewById(R.id.txtFec_proN);
        fec_ult =findViewById(R.id.txtFec_revN);
        fec_pro.setInputType(InputType.TYPE_NULL);
        fec_ult.setInputType(InputType.TYPE_NULL);

        maquina = findViewById(R.id.txtMaquinaN);
        ubicacion = findViewById(R.id.txtubicacionN);

        //Departamentos
        spinner = findViewById(R.id.spinner_depto);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.Departamentos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Status
        spinner2 = findViewById(R.id.spinner_status);
        ArrayAdapter<CharSequence> adapter2 =
                ArrayAdapter.createFromResource(this, R.array.Status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        save = findViewById(R.id.floatsaveM2);


        //Save
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbMaquinas dbMaquinas = new DbMaquinas(NuevoMaquinaActivity.this);

                long id = dbMaquinas.InsertMaq(maquina.getText().toString(),ubicacion.getText().toString(),spinner.getSelectedItem().toString(), fec_ult.getText().toString(),fec_pro.getText().toString(),spinner2.getSelectedItem().toString());

                if (id > 0 ){
                    Toast.makeText(NuevoMaquinaActivity.this, "Registro Maquina Almacenado", Toast.LENGTH_SHORT).show();
                    cleartxt();
                    llenarlista(0,"todos");
                } else {
                    Toast.makeText(NuevoMaquinaActivity.this, maquina.getText().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });



        fec_ult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        NuevoMaquinaActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener , year ,month , day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        fec_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        NuevoMaquinaActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener2 , year ,month , day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1 ;
                String date = dayOfMonth+"/"+month+"/"+year;
                fec_ult.setText(date);
            }
        };

        onDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1 ;
                String date = dayOfMonth+"/"+month+"/"+year;
                fec_pro.setText(date);
            }
        };

    }

    private void cleartxt() {
        maquina.setText("");
        ubicacion.setText("");
        spinner.getItemAtPosition(0);
        fec_ult.setText("");
        fec_pro.setText("");
        spinner2.getItemAtPosition(0);
    }

    private void llenarlista(int tipo,String filmaquina )
    {
        listaMaquinas = findViewById(R.id.listamaquina);
        listaMaquinas.setLayoutManager( new LinearLayoutManager(this));

        DbMaquinas dbMaquinas = new DbMaquinas(NuevoMaquinaActivity.this);

        listaArrayMaquinas = new ArrayList<>();
        ListaMaquinasAdapter adapter = new ListaMaquinasAdapter(dbMaquinas.mostrarMaquinas(tipo, filmaquina));
        listaMaquinas.setAdapter(adapter);
    }




}