package com.example.gymmembershipapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gymmembershipapp.data.local.entity.MemberEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMember(member: MemberEntity): Long

    @Query("SELECT * FROM members ORDER BY name ASC")
    fun getAllMembers(): Flow<List<MemberEntity>>

    @Query("SELECT * FROM members WHERE id = :id")
    fun getMemberById(id: Int): Flow<MemberEntity?>

    @Query("UPDATE members SET points = :points WHERE id = :memberId")
    suspend fun updateMemberPoints(memberId: Int, points: Int)

    @Query("SELECT COUNT(*) FROM members")
    suspend fun countMembers(): Int

    @Delete
    suspend fun deleteMember(member: MemberEntity)
}
