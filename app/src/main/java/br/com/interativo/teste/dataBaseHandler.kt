package br.com.interativo.teste

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteAbortException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.core.content.contentValuesOf

val DATABASE_NAME = "IoniaDB"
val TABLE_NAME = "users"
val COL_USERNAME = "username"
val COL_PASSWORD = "password"
val COL_ID = "id"

class dataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME +" ( " +
                           COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            COL_USERNAME + " VARCHAR(256), " +
                            COL_PASSWORD + " VARCHAR(256)) ";

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun getData(username : String) : String{
         val db = this.readableDatabase

        val cursor = db.rawQuery("select * from users where username = ?", arrayOf<String>(username.trim()))

        if (cursor.moveToNext()){

            var request: String = cursor.getString(cursor.getColumnIndex(COL_USERNAME))

            return request

        }
        var negative: String = "Usuario nao encontrado"
        return negative

    }

    fun insertData(user: userClass){
        val db = this.writableDatabase
        var values = ContentValues()
        values.put(COL_USERNAME, user.Username)
        values.put(COL_PASSWORD, user.Password)

        var result = db.insert(TABLE_NAME, null, values)
        if(result == -1.toLong())
            Toast.makeText(context,"Falha ao cadastrar usuário", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Sucesso ao cadastrar usuário", Toast.LENGTH_SHORT).show()
    }


}

