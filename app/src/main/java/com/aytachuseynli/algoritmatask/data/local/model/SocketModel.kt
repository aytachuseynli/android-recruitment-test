package com.aytachuseynli.algoritmatask.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SocketModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val change: String,
    val name: String,
    val valueOne: String,
    val valueTwo: String,
    val valueThree: String,
    val valueFour: String,
    val rating: String,
    val date: String
)