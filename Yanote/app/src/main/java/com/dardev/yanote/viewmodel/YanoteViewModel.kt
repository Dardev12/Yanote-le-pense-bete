package com.dardev.yanote.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dardev.yanote.model.Yanote
import com.dardev.yanote.repository.NoteRepository
import kotlinx.coroutines.launch

class YanoteViewModel(
        app:Application,
        private val noteRepository: NoteRepository
): AndroidViewModel(app){

    fun addYanote(yanote: Yanote)=viewModelScope.launch {
        noteRepository.addYanote(yanote)
    }

    fun updateYanote(yanote: Yanote)=viewModelScope.launch {
        noteRepository.updateYanote(yanote)
    }

    fun deleteYanote(yanote: Yanote)=viewModelScope.launch {
        noteRepository.eraseYanote(yanote)
    }

    fun getAllYanote()=noteRepository.getAllYanote()

    fun searchYanote(query:String?)=noteRepository.searchYanote(query)
}