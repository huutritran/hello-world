package com.example.trith.servicedemo.db.entity

import android.arch.persistence.room.Entity
import com.example.trith.servicedemo.db.StepCounterDb

/**
 * Created by tri.th on 23/08/2018.
 */
@Entity(primaryKeys = arrayOf("date"))
data class StepInDay(val step:Int,
                     val date:Long)