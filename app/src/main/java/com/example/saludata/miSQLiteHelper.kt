package com.example.saludata

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class miSQLiteHelper(context: Context) : SQLiteOpenHelper(
    context, "users.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE users " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        "nombre TEXT, email TEXT, password TEXT)"
        db!!.execSQL(ordenCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //solo para pruebas
        val ordenBorrado = "DROP TABLE IF EXISTS users"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    fun addValue(nombre: String, email: String, password: String){
        val values = ContentValues()
        values.put("nombre", nombre)
        values.put("email", email)
        values.put("password", password)

        val db = this.writableDatabase
        db.insert("users", null, values)
        db.close()
    }

}