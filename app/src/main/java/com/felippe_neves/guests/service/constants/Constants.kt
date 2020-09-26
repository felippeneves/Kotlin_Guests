package com.felippe_neves.guests.service.constants

class Constants private constructor()
{
    companion object
    {
        const val GUEST_ID = "GUEST_ID"
    }

    object FILTER
    {
        const val EMPTY = 0
        const val PRESENT = 1
        const val ABSENT = 2
    }
}