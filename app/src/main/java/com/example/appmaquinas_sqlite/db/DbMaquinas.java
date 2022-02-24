package com.example.appmaquinas_sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.appmaquinas_sqlite.entidades.EMaquinas;

import java.util.ArrayList;

public class DbMaquinas extends DbHelper {

    Context context;

    public DbMaquinas(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    public long InsertMaq(String maquina, String ubicacion , String departamento , String ult_f , String pro_f, String status){
        long id = 0;
        String cliente = "NO ASIGNADO";

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("maquina", maquina);
            values.put("ubicacion", ubicacion);
            values.put("departamento", departamento);
            values.put("ult_fecha_revisada" , ult_f);
            values.put("pro_fecha_revisar" , pro_f);
            values.put("status" , status);
            values.put("cliente",cliente);
            id = db.insert(TABLE_MAQUINAS, null, values);
            db.close();
        }catch (Exception ex){
            ex.toString();
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
        }
        return id;


    }


    public boolean UpdateMaq(int id , String maquina, String ubicacion , String departamento , String ult_f , String pro_f, String status){
        Boolean correcto = false;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id", id);
            values.put("maquina", maquina);
            values.put("ubicacion", ubicacion);
            values.put("departamento", departamento);
            values.put("ult_fecha_revisada" , ult_f);
            values.put("pro_fecha_revisar" , pro_f);
            values.put("status" , status);
            db.update(TABLE_MAQUINAS,values,"id=?",new String[]{Integer.toString(id)});
            db.close();
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
            correcto = false;
        }
        return correcto;
    }


    public ArrayList<EMaquinas> mostrarMaquinas(int tipo, String filmaquina ){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<EMaquinas> listaMaquinas = new ArrayList<>();
        EMaquinas maquinas = null;
        Cursor cursorMaquinas = null;
        if (tipo==0){
            cursorMaquinas  = db.rawQuery("SELECT * FROM " + TABLE_MAQUINAS + " ORDER BY MAQUINA ",null);
        }
        else{
            cursorMaquinas = db.rawQuery("SELECT * FROM " + TABLE_MAQUINAS +" WHERE  MAQUINA LIKE '%" +filmaquina+"%' ORDER BY MAQUINA",null);
        }

        if (cursorMaquinas.moveToFirst()){
            do{
                maquinas = new EMaquinas();
                maquinas.setId(cursorMaquinas.getInt(0));
                maquinas.setMaquina(cursorMaquinas.getString(1));
                maquinas.setUbicacion(cursorMaquinas.getString(2));
                maquinas.setDepartamento(cursorMaquinas.getString(3));
                maquinas.setUtl_fecha_revisada(cursorMaquinas.getString(4));
                maquinas.setPro_fecha_revisar(cursorMaquinas.getString(5));
                maquinas.setStatus(cursorMaquinas.getString(6));
                maquinas.setCliente(cursorMaquinas.getString(7));

                listaMaquinas.add(maquinas);
            } while (cursorMaquinas.moveToNext());
        }

        cursorMaquinas.close();

        return listaMaquinas;

    }

    public EMaquinas verMaquina(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        EMaquinas maquina = null;
        Cursor cursorMaquinas = null ;
        cursorMaquinas = db.rawQuery("SELECT * FROM " + TABLE_MAQUINAS + " WHERE ID= " +id + " LIMIT 1" ,null);

        if (cursorMaquinas.moveToFirst()){
            maquina = new EMaquinas();
            maquina.setId(cursorMaquinas.getInt(0));
            maquina.setMaquina(cursorMaquinas.getString(1));
            maquina.setUbicacion(cursorMaquinas.getString(2));
            maquina.setDepartamento(cursorMaquinas.getString(3));
            maquina.setUtl_fecha_revisada(cursorMaquinas.getString(4));
            maquina.setPro_fecha_revisar(cursorMaquinas.getString(5));
            maquina.setStatus(cursorMaquinas.getString(6));
            maquina.setCliente(cursorMaquinas.getString(7));

        }

        cursorMaquinas.close();
        return maquina;


    }



    public boolean eliminarMaquina(int id){
        boolean correcto = false    ;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM "+ TABLE_MAQUINAS + "  WHERE ID='" +id + "'" );
            correcto = true ;
        }catch (Exception ex){
            ex.toString();
            correcto = false   ;
        }
        finally {
            db.close();
        }
        return correcto;
    }



}
