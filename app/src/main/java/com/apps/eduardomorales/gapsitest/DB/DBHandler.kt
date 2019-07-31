package com.apps.eduardomorales.gapsitest.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class DBHandler(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME " +
                "($FIELD_NAME1 String PRIMARY KEY)"
        p0?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun getAllSearchCriteria(): List<String> {
        var criteriaList: MutableList<String> = ArrayList()

        val db = readableDatabase
        val query = "SELECT $FIELD_NAME1 FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    var searchCriteria = cursor.getString(cursor.getColumnIndex(FIELD_NAME1))

                    criteriaList.add(searchCriteria)

                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()

        return criteriaList
    }

    fun saveSearchCriteria(criteria: String): Boolean{

        val db = this.writableDatabase

        val value = ContentValues()
        value.put(FIELD_NAME1, criteria)

        val inserted = db.replace(TABLE_NAME,null,value)
        db.close()

        return (Integer.parseInt("$inserted") != -1)
    }

    companion object {
        private val DB_NAME = "Gapsitest"
        private val DB_VERSION = 1;
        private val TABLE_NAME = "search_criteria"
        private val FIELD_NAME1 = "value"

    }
}