package com.aytachuseynli.algoritmatask.common.utils

import com.aytachuseynli.algoritmatask.data.local.model.SocketModel
import org.json.JSONObject

fun extractDataFromJSONObject(data: JSONObject): SocketModel {
    // Extract data from JSONObject
    val change = data.getString("change")
    val name = data.getString("name")
    val valueOne = data.getString("valueOne")
    val valueTwo = data.getString("valueTwo")
    val valueThree = data.getString("valueThree")
    val valueFour = data.getString("valueFour")
    val rating = data.getString("rating")
    val date = data.getString("date")

    // Create and return SocketModel instance
    return SocketModel(
        change = change,
        name = name,
        valueOne = valueOne,
        valueTwo = valueTwo,
        valueThree = valueThree,
        valueFour = valueFour,
        rating = rating,
        date = date
    )
}