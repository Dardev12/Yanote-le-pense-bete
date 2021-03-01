package com.dardev.yanote.bd

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dardev.yanote.model.Yanote

@Dao
interface YanoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  addYanote(yanote:Yanote)

    @Update
    suspend fun  updateYanote(yanote:Yanote)

    @Delete
    suspend fun eraseYanote(yanote: Yanote)

    @Query("SELECT * FROM yanotes ORDER BY TAG DESC")
    fun getAllNotes():LiveData<List<Yanote>>

    @Query("SELECT * FROM yanotes WHERE yaTitre LIKE:query OR yaContenu LIKE :query")
    fun searchYanote(query:String?):LiveData<List<Yanote>>

}