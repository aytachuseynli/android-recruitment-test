package com.aytachuseynli.algoritmatask.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "socket_model_table")
data class SocketModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val upDown: String,
    val name: String,
    val valueOne: String,
    val valueTwo: String,
    val valueThree: String,
    val valueFour: String,
    val rating: String,
    val date: String
)