package com.example.trith.servicedemo.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.trith.servicedemo.db.entity.StepAllDay
import com.example.trith.servicedemo.db.entity.StepInDay

/**
 * Created by tri.th on 23/08/2018.
 */
@Dao
interface StepDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStepInDay(vararg step: StepInDay)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStepAllDay(vararg step: StepAllDay)

    @Query("SELECT * FROM stepallday WHERE datestring = :dateString")
    fun getStepInDay(dateString: String):StepAllDay?

}