package com.example.appmaquinas_sqlite.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION =   3;
    private static final String DATABASE_NOMBRE = "maquina.db";
    public static final String TABLE_MAQUINAS = "t_maquinas";
    public static final String TABLE_CLIENTES = "t_clientes";
    public static final String TABLE_MOVIMS= "t_movims";



    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_MAQUINAS + "(" +
                "id INTEGER  primary key autoincrement," +
                "maquina TEXT NOT NULL," +
                "ubicacion TEXT ," +
                "departamento TEXT ,"+
                "ult_fecha_revisada TEXT ," +
                "pro_fecha_revisar TEXT," +
                "status TEXT," +
                "cliente TEXT"+")");

/*

        db.execSQL("CREATE TABLE " + TABLE_CLIENTES + "(" +
                "id INTEGER  primary key autoincrement," +
                "nombre TEXT NOT NULL," +
                "telefono TEXT ," +
                "direccion TEXT ,"+
                "fecha_contrato TEXT ," +
                "fecha_fin TEXT," +
                "procentaje REAL," +
                "maquina_id INTEGER"+")");

        db.execSQL("CREATE TABLE " + TABLE_MOVIMS + "(" +
                "id INTEGER  primary key autoincrement," +
                "maquina_id TEXT NOT NULL," +
                "cliente_id TEXT ," +
                "porcentaje REAL ," +
                "fecha TEXT ,"+
                "valor_corte REAL ," +
                "valor_cliente REAL," +
                "valor_ganacial REAL"+")");
*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+ TABLE_MAQUINAS);
        onCreate(db);
    }
}
