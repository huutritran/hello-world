package com.example.trith.servicedemo

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by tri.th on 22/08/2018.
 */
fun Date.isTheSameDateWith(anotherDate:Date):Boolean{
    val simplePattern = "dd/MM/yyyy"
    val formatter = SimpleDateFormat(simplePattern,Locale.getDefault())
    try {
        val date1AsLong = formatter.format(this)
        val date2AsLong = formatter.format(anotherDate)
        return date1AsLong == date2AsLong
    }catch (ex:Exception){
        ex.toString()
    }
    return false
}
