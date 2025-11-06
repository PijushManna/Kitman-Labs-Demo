package com.kitman.utils

import com.kitman.utils.Convertors.inputFormat
import com.kitman.utils.Convertors.outputFormat
import java.text.SimpleDateFormat
import java.util.Locale

object Convertors {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd-MM-yyyy, HH:mm a", Locale.getDefault())
}

fun String.toReadableDate(): String{
    val date = inputFormat.parse(this) ?: return ""
    return outputFormat.format(date)
}