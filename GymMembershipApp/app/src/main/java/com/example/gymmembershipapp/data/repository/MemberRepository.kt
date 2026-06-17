package com.example.gymmembershipapp.data.repository

import com.example.gymmembershipapp.data.local.dao.MemberDao
import com.example.gymmembershipapp.data.local.entity.MemberEntity
import kotlinx.coroutines.flow.Flow

class MemberRepository(private val memberDao: MemberDao) {

    fun getAllMembers(): Flow<List<MemberEntity>> = memberDao.getAllMembers()

    fun getMemberById(id: Int): Flow<MemberEntity?> = memberDao.getMemberById(id)

    suspend fun insertMember(member: MemberEntity): Long = memberDao.insertMember(member)

    suspend fun updateMemberPoints(memberId: Int, points: Int) =
        memberDao.updateMemberPoints(memberId, points)

    suspend fun deleteMember(member: MemberEntity) = memberDao.deleteMember(member)
}
