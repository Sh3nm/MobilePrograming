package com.example.gymmembershipapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gymmembershipapp.data.local.dao.AccountDao
import com.example.gymmembershipapp.data.local.dao.MemberDao
import com.example.gymmembershipapp.data.local.dao.WorkoutDao
import com.example.gymmembershipapp.data.local.entity.AccountEntity
import com.example.gymmembershipapp.data.local.entity.MemberEntity
import com.example.gymmembershipapp.data.local.entity.WorkoutEntity
import com.example.gymmembershipapp.domain.hashPassword
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [MemberEntity::class, WorkoutEntity::class, AccountEntity::class],
    version = 2,
    exportSchema = false
)
abstract class FitTrackDatabase : RoomDatabase() {

    abstract fun memberDao(): MemberDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun accountDao(): AccountDao

    companion object {
        const val DATABASE_NAME = "fittrack_database"

        @Volatile
        private var INSTANCE: FitTrackDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): FitTrackDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FitTrackDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .addCallback(SeedCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    /** Seeds a few demo members + workouts the first time the DB is created. */
    private class SeedCallback(private val scope: CoroutineScope) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    seed(database.memberDao(), database.workoutDao(), database.accountDao())
                }
            }
        }

        private suspend fun seed(memberDao: MemberDao, workoutDao: WorkoutDao, accountDao: AccountDao) {
            // Demo login account: admin@fittrack.com / admin123
            accountDao.insertAccount(
                AccountEntity(
                    name = "Admin FitTrack",
                    email = "admin@fittrack.com",
                    passwordHash = hashPassword("admin123")
                )
            )

            val budiId = memberDao.insertMember(
                MemberEntity(
                    name = "Budi Santoso",
                    email = "budi@mail.com",
                    phone = "081234567890",
                    joinDate = "12 Jan 2026",
                    points = 47,
                    memberId = "FT-1000001"
                )
            ).toInt()
            val sariId = memberDao.insertMember(
                MemberEntity(
                    name = "Sari Rahayu",
                    email = "sari@mail.com",
                    phone = "081298765432",
                    joinDate = "03 Feb 2026",
                    points = 150,
                    memberId = "FT-1000002"
                )
            ).toInt()
            val agusId = memberDao.insertMember(
                MemberEntity(
                    name = "Agus Pratama",
                    email = "agus@mail.com",
                    phone = "081377788899",
                    joinDate = "20 Mar 2026",
                    points = 268,
                    memberId = "FT-1000003"
                )
            ).toInt()

            workoutDao.insertWorkout(
                WorkoutEntity(memberId = budiId, workoutType = "Cardio", duration = 60, pointEarned = 12, date = "12 Jun 2026, 07:30")
            )
            workoutDao.insertWorkout(
                WorkoutEntity(memberId = budiId, workoutType = "Strength", duration = 45, pointEarned = 9, date = "10 Jun 2026, 18:00")
            )
            workoutDao.insertWorkout(
                WorkoutEntity(memberId = sariId, workoutType = "Yoga", duration = 90, pointEarned = 18, date = "11 Jun 2026, 06:00")
            )
            workoutDao.insertWorkout(
                WorkoutEntity(memberId = agusId, workoutType = "HIIT", duration = 30, pointEarned = 6, date = "09 Jun 2026, 19:30")
            )
        }
    }
}
