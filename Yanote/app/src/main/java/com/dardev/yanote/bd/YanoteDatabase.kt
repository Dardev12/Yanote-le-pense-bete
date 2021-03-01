package com.dardev.yanote.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dardev.yanote.model.Yanote

@Database(entities = [Yanote::class],version = 1)
abstract class YanoteDatabase:RoomDatabase() {

    abstract fun getNoteDao():YanoteDAO

    companion object{

        @Volatile
        private var instance:YanoteDatabase?= null
        private val Lock=Any()

        operator fun invoke(context: Context)=instance?: synchronized(Lock){
            instance?:
            createDatabase(context).also {
                instance=it
            }
        }

        private fun createDatabase(context: Context)= Room.databaseBuilder(
                context.applicationContext,
                YanoteDatabase::class.java,
                "bd_yanote"
        ).build()
    }
}