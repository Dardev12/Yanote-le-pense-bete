package com.dardev.yanote.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName="yanotes")
@Parcelize
data class Yanote (
        @PrimaryKey(autoGenerate = true)
        val TAG:Int,
        val yaTitre:String,
        val yaContenu:String
        ):Parcelable