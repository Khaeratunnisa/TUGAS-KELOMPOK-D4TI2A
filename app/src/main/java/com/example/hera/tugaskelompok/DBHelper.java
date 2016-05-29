package com.example.hera.tugaskelompok;

/**
 * Created by Hera on 5/25/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_inventory";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "user";
    private static final String TABLE_BARANG = "barang";
    private static final String TABLE_SUPPLIER = "supplier";
    private static final String TABLE_KARYAWAN = "karyawan";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private SQLiteDatabase db;

    private static final String CREATE_TABLE_USER = "create table " + TABLE_USER +
            "(username string primary key, password text);";

    private static final String CREATE_TABLE_BARANG = "create table " + TABLE_BARANG +
            "(id_barang integer(6) not null, " +
            "nama_barang varchar(30) not null, " +
            "jenis_barang varchar(30) not null," +
            "jumlah_barang integer(10) not null, " +
            "harga_barang integer(10) not null, " +
            "id_supplier integer(6) not null);";

    private static final String CREATE_TABLE_SUPPLIER = "create table " + TABLE_SUPPLIER +
            "(id_supplier integer(6) nol null, " +
            "nama_supplier varchar(30) not null, " +
            "alamat_supplier varchar(50) not null," +
            "telp_supplier varchar(13) not null,"+
            "primary key(id_supplier));";

    private static final String CREATE_TABLE_KARYAWAN = "create table " + TABLE_KARYAWAN +
            "(id_karyawan integer(6) not null," +
            "nama_karyawan varchar(30) not null, " +
            "alamat_karyawan varchar(50) not null," +
            "telp_karyawan varchar(13) not null," +
            "primary key(id_karyawan);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_BARANG);
        db.execSQL(CREATE_TABLE_SUPPLIER);
        db.execSQL(CREATE_TABLE_KARYAWAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARANG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUPPLIER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KARYAWAN);
        onCreate(db);
    }

    public void SimpanUser(String Username, String Password) {
        ContentValues values = new ContentValues();
        values.put(USERNAME, Username);
        values.put(PASSWORD, Password);
        db.insert(TABLE_USER, null, values);
    }
    public void HapusUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_USER);
    }
}