package de.dhbw.heidenheim.schuetz.simplenavigation.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey
    val id: Int = 1, // nur ein fixes Profil
    val name: String,
    val imageUrl: String,
    val bio: String
)