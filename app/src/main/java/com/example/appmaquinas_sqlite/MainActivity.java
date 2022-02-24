package com.example.appmaquinas_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appmaquinas_sqlite.adaptores.ListaMaquinasAdapter;
import com.example.appmaquinas_sqlite.db.DbHelper;
import com.example.appmaquinas_sqlite.db.DbMaquinas;
import com.example.appmaquinas_sqlite.entidades.EMaquinas;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

   /* private Button menu1,menu2,menu3 ;*/
   EditText buscar;
    //iniciar Lista
    RecyclerView listaMaquinas ;
    ArrayList<EMaquinas> listaArrayMaquinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create DATABASE
        creardb();

        //Llenar lista de las maquinas
        llenarlista(0,"todos");

        buscar = findViewById(R.id.txtbuscarMaquina);

        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().equals("")){
                    llenarlista(0,"todos");
                }
                else{
                    llenarlista(1,s.toString());
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



      /*  menu1 = findViewById(R.id.btMenu1);
        menu1.setOnClickListener(new click());
        menu2 = findViewById(R.id.btMenu2);
        menu2.setOnClickListener(new click());
        menu3 = findViewById(R.id.btMenu3);
        menu3.setOnClickListener(new click());*/




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_refresh:
                buscar.setText("");
                llenarlista(0,"todos");
                llenarlista(0,"todos");
                return true;

            case R.id.action_create:
                Intent i4 = new Intent(MainActivity.this, NuevoMaquinaActivity.class);
                startActivity(i4);
                Toast.makeText(MainActivity.this, "Menu 1" , Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_maquina:
                Intent i = new Intent(MainActivity.this, NuevoMaquinaActivity.class);
                startActivity(i);
                Toast.makeText(MainActivity.this, "Menu 1" , Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_cliente:
                Intent i2 = new Intent(MainActivity.this, NuevoClienteActivity.class);
                startActivity(i2);
                Toast.makeText(MainActivity.this, "Menu 2" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_movims:
                Intent i3 = new Intent(MainActivity.this, NuevoMovimientoActivity.class);
                startActivity(i3);
                Toast.makeText(MainActivity.this, "Menu 3" , Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

  /*  private class click implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.btMenu1:
                    Intent i = new Intent(MainActivity.this, NuevoMaquinaActivity.class);
                    startActivity(i);
                    Toast.makeText(MainActivity.this, "Menu 1" , Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btMenu2:
                    Intent i2 = new Intent(MainActivity.this, NuevoClienteActivity.class);
                    startActivity(i2);
                    Toast.makeText(MainActivity.this, "Menu 2" , Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btMenu3:
                    Intent i3 = new Intent(MainActivity.this, NuevoMovimientoActivity.class);
                    startActivity(i3);
                    Toast.makeText(MainActivity.this, "Menu 3" , Toast.LENGTH_SHORT).show();
                    break;


            }

        }
    }*/

    public void creardb(){
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null){
            Toast.makeText(MainActivity.this, "ACCESS DATA BASE", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "ERROR AL CREAR BASE DE DATOS", Toast.LENGTH_SHORT).show();
        }
    }


    private void llenarlista(int tipo,String filmaquina )
    {
        listaMaquinas = findViewById(R.id.listaMaquinas);
        listaMaquinas.setLayoutManager( new LinearLayoutManager(this));

        DbMaquinas dbMaquinas = new DbMaquinas(MainActivity.this);

        listaArrayMaquinas = new ArrayList<>();
        ListaMaquinasAdapter adapter = new ListaMaquinasAdapter(dbMaquinas.mostrarMaquinas(tipo, filmaquina));
        listaMaquinas.setAdapter(adapter);
    }


}