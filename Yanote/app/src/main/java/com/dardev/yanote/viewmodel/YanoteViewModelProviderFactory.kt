package com.dardev.yanote.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dardev.yanote.repository.NoteRepository

class YanoteViewModelProviderFactory(
        val app:Application,
        private val yanoteRepository: NoteRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return YanoteViewModel(app,yanoteRepository)as T
    }
}