package com.example.gymmembershipapp.ui.navigation

object Routes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val ADD_MEMBER = "add_member"

    const val MEMBER_CARD = "member_card/{memberId}"
    const val WORKOUT = "workout/{memberId}"
    const val REWARD = "reward/{memberId}"

    const val ARG_MEMBER_ID = "memberId"

    fun memberCard(memberId: Int) = "member_card/$memberId"
    fun workout(memberId: Int) = "workout/$memberId"
    fun reward(memberId: Int) = "reward/$memberId"
}
