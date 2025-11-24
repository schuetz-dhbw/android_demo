package de.dhbw.heidenheim.schuetz.simplenavigation.data.repository

import de.dhbw.heidenheim.schuetz.simplenavigation.data.local.UserProfileDao
import de.dhbw.heidenheim.schuetz.simplenavigation.data.local.UserProfileEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfileRepository @Inject constructor(
    private val userProfileDao: UserProfileDao
) {
    fun getUserProfile(): Flow<UserProfileEntity?> {
        return userProfileDao.getUserProfile()
    }

    suspend fun saveUserProfile(name: String, imageUrl: String, bio: String) {
        val profile = UserProfileEntity(
            id = 1,
            name = name,
            imageUrl = imageUrl,
            bio = bio
        )
        userProfileDao.insertUserProfile(profile)
    }
}