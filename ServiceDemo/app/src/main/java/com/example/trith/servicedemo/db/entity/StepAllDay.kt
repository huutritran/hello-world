package com.example.trith.servicedemo.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.trith.servicedemo.db.StepCounterDb

/**
 * Created by tri.th on 23/08/2018.
 */
@Entity(primaryKeys = arrayOf("date"))
data class StepAllDay(
        val step: Int,
        val date: Long,
        val dateString: String
)