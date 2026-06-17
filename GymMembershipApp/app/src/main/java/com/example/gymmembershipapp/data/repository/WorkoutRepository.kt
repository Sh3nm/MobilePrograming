package com.example.gymmembershipapp.data.repository

import com.example.gymmembershipapp.data.local.dao.WorkoutDao
import com.example.gymmembershipapp.data.local.entity.WorkoutEntity
import kotlinx.coroutines.flow.Flow

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    fun getWorkoutsByMember(memberId: Int): Flow<List<WorkoutEntity>> =
        workoutDao.getWorkoutsByMember(memberId)

    suspend fun insertWorkout(workout: WorkoutEntity): Long = workoutDao.insertWorkout(workout)

    suspend fun deleteWorkout(workout: WorkoutEntity) = workoutDao.deleteWorkout(workout)
}
