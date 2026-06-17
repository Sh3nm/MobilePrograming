package com.example.gymmembershipapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gymmembershipapp.data.local.entity.WorkoutEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutEntity): Long

    @Query("SELECT * FROM workouts WHERE memberId = :memberId ORDER BY id DESC")
    fun getWorkoutsByMember(memberId: Int): Flow<List<WorkoutEntity>>

    @Delete
    suspend fun deleteWorkout(workout: WorkoutEntity)
}
