package com.dardev.yanote.repository

import com.dardev.yanote.bd.YanoteDatabase
import com.dardev.yanote.model.Yanote

class NoteRepository(private val db:YanoteDatabase) {

    suspend fun  addYanote(yanote: Yanote)=db.getNoteDao().addYanote(yanote)
    suspend fun updateYanote(yanote: Yanote)=db.getNoteDao().updateYanote(yanote)
    suspend fun eraseYanote(yanote: Yanote)=db.getNoteDao().eraseYanote(yanote)
    fun getAllYanote()=db.getNoteDao().getAllNotes()
    fun searchYanote(query:String?)=db.getNoteDao().searchYanote(query)
}