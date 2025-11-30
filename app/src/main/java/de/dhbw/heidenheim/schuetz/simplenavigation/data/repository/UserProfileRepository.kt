package de.dhbw.heidenheim.schuetz.simplenavigation.data.repository

import de.dhbw.heidenheim.schuetz.simplenavigation.data.local.UserProfileDao
import de.dhbw.heidenheim.schuetz.simplenavigation.data.local.UserProfileEntity
import de.dhbw.heidenheim.schuetz.simplenavigation.data.remote.RandomUserApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfileRepository @Inject constructor(
    private val userProfileDao: UserProfileDao,
    private val randomUserApi: RandomUserApi
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

    suspend fun loadRandomUser(): Result<UserProfile> {
        return try {
            val response = randomUserApi.getRandomUsers()
            val user = response.userList.first()

            Result.success(
                UserProfile(
                    name = user.name.fullName(),
                    imageUrl = user.picture.medium,
                    bio = user.location.fullAddress()
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}