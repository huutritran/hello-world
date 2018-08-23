package com.example.trith.servicedemo.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.trith.servicedemo.db.dao.StepDao
import com.example.trith.servicedemo.db.entity.StepAllDay
import com.example.trith.servicedemo.db.entity.StepInDay

/**
 * Created by tri.th on 23/08/2018.
 */
@Database(entities = arrayOf(StepInDay::class,StepAllDay::class),version = 1)
abstract class StepCounterDb: RoomDatabase() {
    companion object {
        const val TABLE_STEP_IN_DAY = "TABLE_STEP_IN_DAY"
        const val TABLE_STEP_ALL_DAY = "TABLE_STEP_ALL_DAY"
    }
    abstract fun StepDao():StepDao
}