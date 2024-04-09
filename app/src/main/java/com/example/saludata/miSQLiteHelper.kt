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
                        "username TEXT, password TEXT)"
        db!!.execSQL(ordenCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //solo para pruebas
        val ordenBorrado = "DROP TABLE IF EXISTS users"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    fun addValue(username: String, password: String){
        val values = ContentValues()
        values.put("username", username)
        values.put("password", password)

        val db = this.writableDatabase
        db.insert("users", null, values)
        db.close()
    }

    fun checkUser(username: String, password: String): Boolean{
        val db = this.writableDatabase
        val query = "SELECT * FROM users WHERE username = '$username' and password = '$password'"
        val cursor = db.rawQuery(query, null)
        if (cursor.count <= 0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

}