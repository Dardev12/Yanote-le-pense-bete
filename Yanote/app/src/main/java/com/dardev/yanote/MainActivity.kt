package com.dardev.yanote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dardev.yanote.bd.YanoteDatabase
import com.dardev.yanote.databinding.ActivityMainBinding
import com.dardev.yanote.repository.NoteRepository
import com.dardev.yanote.viewmodel.YanoteViewModel
import com.dardev.yanote.viewmodel.YanoteViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var yanoteViewModel: YanoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setUpViewModel()
    }

    private fun setUpViewModel(){
        val noteRepository=NoteRepository(
                YanoteDatabase(this)
        )
        val viewModelProviderFactory=
                YanoteViewModelProviderFactory(
                        application,
                        noteRepository
                )
        yanoteViewModel=ViewModelProvider(
                this,
                viewModelProviderFactory
        ).get(YanoteViewModel::class.java)
    }
}