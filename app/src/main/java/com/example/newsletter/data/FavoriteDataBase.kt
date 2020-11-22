package com.example.newsletter.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class FavoriteDataBase(context: Context?):
        SQLiteOpenHelper(context, DATABASE_NAME, null, DB_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE)
    }
    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}

    // insert data into database
    fun insertIntoTheDatabase(
            id: String?,
            title: String,
            description: String,
            author: String,
            urlToImage: String,
            url: String,
            favorite: Boolean
    ) {
        val db: SQLiteDatabase
        db = this.writableDatabase
        val cv = ContentValues()
        cv.put(ARTICLE_TITLE, title)
        cv.put(ARTICLE_DESCRIPTION, description)
        cv.put(ARTICLE_AUTHOR, author)
        cv.put(ARTICLE_IMAGE, urlToImage)
        cv.put(KEY_ID, id)
        cv.put(ARTICLE_URL,url)
        cv.put(FAVORITE_STATUS, favorite)
        db.insert(TABLE_NAME, null, cv)
        Log.d(
                "FavDB Status",
                "$title, favstatus - $favorite - . $cv"
        )
    }
    // read all data
    fun read_all_data(string: String): Cursor {
        val db = this.readableDatabase
        val sql =
                "SELECT * FROM $TABLE_NAME WHERE $KEY_ID=$string"
        return db.rawQuery(sql, null, null)
    }
    // remove line from database
    fun remove_fav(id: String) {
        val db = this.writableDatabase
        val sql =
                "UPDATE $TABLE_NAME SET  $FAVORITE_STATUS =false WHERE $KEY_ID=$id"
        db.execSQL(sql)
        Log.d("remove", id)
    }
    // select all favorite list
    fun select_all_favorite_list(): Cursor {
        val db = this.readableDatabase
        val sql =
                "SELECT * FROM $TABLE_NAME WHERE $FAVORITE_STATUS =true"
        return db.rawQuery(sql, null, null)
    }

    companion object {
        private const val DB_VERSION = 1
        private const val DATABASE_NAME = "ArticleDB"
        private const val TABLE_NAME = "favoriteTable"
        var KEY_ID = "id"
        var ARTICLE_TITLE = "articleTitle"
        var ARTICLE_DESCRIPTION = "articleDescription"
        var ARTICLE_AUTHOR = "articleAuthor"
        var ARTICLE_IMAGE = "articleImage"
        var ARTICLE_URL = "articleUrl"
        var FAVORITE_STATUS = "fStatus"

        private val CREATE_TABLE =
                ("CREATE TABLE " + TABLE_NAME + "("
                        + KEY_ID + " TEXT," + ARTICLE_TITLE + " TEXT," + ARTICLE_DESCRIPTION + " TEXT," + ARTICLE_AUTHOR + " TEXT,"
                        + ARTICLE_URL + " TEXT," + ARTICLE_IMAGE + " TEXT," + FAVORITE_STATUS + " BOOLEAN)")
    }
}



