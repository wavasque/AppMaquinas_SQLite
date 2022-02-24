package com.example.appmaquinas_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.appmaquinas_sqlite.db.DbMaquinas;
import com.example.appmaquinas_sqlite.entidades.EClientes;
import com.example.appmaquinas_sqlite.entidades.EMaquinas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class ViewMaquinaActivity extends AppCompatActivity {


    EditText fec_pro;
    EditText fec_ult;
    DatePickerDialog.OnDateSetListener onDateSetListener,onDateSetListener2;
    final Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    Spinner spinner,spinner2 ;
    FloatingActionButton save,edit,delete ;
    EditText maquina,ubicacion ;

    EMaquinas maquinas;
    int id = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_maquina);

        fec_pro = findViewById(R.id.txtFec_proN2);
        fec_ult =findViewById(R.id.txtFec_revN2);
        fec_pro.setInputType(InputType.TYPE_NULL);
        fec_ult.setInputType(InputType.TYPE_NULL);

        maquina = findViewById(R.id.txtMaquinaN2);
        ubicacion = findViewById(R.id.txtubicacionN2);

        //Departamentos
        spinner = findViewById(R.id.spinner_depto2);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.Departamentos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Status
        spinner2 = findViewById(R.id.spinner_status2);
        ArrayAdapter<CharSequence> adapter2 =
                ArrayAdapter.createFromResource(this, R.array.Status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        save = findViewById(R.id.floatsaveM2);
        edit = findViewById(R.id.floateditM2);
        delete = findViewById(R.id.floatdeleteM2);


        //fechas
        fec_ult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ViewMaquinaActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener , year ,month , day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        fec_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ViewMaquinaActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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

        //Recibir Parametros
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }

            DbMaquinas dbMaquinas = new DbMaquinas(ViewMaquinaActivity.this);
            maquinas = dbMaquinas.verMaquina(id);

            if (maquinas != null) {
                save.setVisibility(View.INVISIBLE);
                maquina.setText(maquinas.getMaquina());
                ubicacion.setText(maquinas.getUbicacion());
                //Departamento
                //Spinner s = (Spinner) findViewById(R.id.spinner_depto2);
                int i;
                for(i=0; i < adapter.getCount(); i++) {
                    if(maquinas.getDepartamento().trim().equals(adapter.getItem(i).toString())){
                        spinner.setSelection(i);
                        break;
                    }
                }
                fec_ult.setText(maquinas.getUtl_fecha_revisada());
                fec_pro.setText(maquinas.getPro_fecha_revisar());

                //Status
                for(i=0; i < adapter2.getCount(); i++) {
                    if(maquinas.getStatus().trim().equals(adapter2.getItem(i).toString())){
                        spinner2.setSelection(i);
                        break;
                    }
                }

                //Deshabilitar los campos
                maquina.setInputType(InputType.TYPE_NULL);
                ubicacion.setInputType(InputType.TYPE_NULL);
                fec_pro.setInputType(InputType.TYPE_NULL);
                fec_ult.setInputType(InputType.TYPE_NULL);
                spinner.setEnabled(false);
                spinner2.setEnabled(false);


                Toast.makeText(this, "Localizado...."+maquina.getText() , Toast.LENGTH_SHORT).show();
            }

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(ViewMaquinaActivity.this, EditMaquinaActivity.class);
                    intent.putExtra("ID", id);
                    startActivity(intent);
                }
            });


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ViewMaquinaActivity.this);
                    builder.setMessage("? DESEA ELIMINAR ESTE REGISTRO ?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dbMaquinas.eliminarMaquina(id)) {
                                lista();
                            }
                        }
                    })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(ViewMaquinaActivity.this, "OPCION CANCELADA...", Toast.LENGTH_SHORT).show();
                                    lista();
                                }
                            }).show();
                }
            });

        }

    }

    public void  lista(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
