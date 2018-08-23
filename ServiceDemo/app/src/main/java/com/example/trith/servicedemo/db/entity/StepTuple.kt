package com.example.trith.servicedemo.db.entity

import android.arch.persistence.room.ColumnInfo

/**
 * Created by tri.th on 23/08/2018.
 */
data class StepTuple(
        @ColumnInfo(name = "step")
        val step:Int,

        @ColumnInfo(name = "date")
        val date: Long
)