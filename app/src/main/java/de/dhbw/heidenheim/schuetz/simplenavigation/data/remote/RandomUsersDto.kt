package de.dhbw.heidenheim.schuetz.simplenavigation.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomUsersDto(
    @SerialName("results")
    val userList: List<UserDto>
)

@Serializable
data class UserDto(
    val name: NameDto,
    val location: LocationDto,
    val picture: PictureDto
)

@Serializable
data class NameDto(
    val first: String,
    val last: String
) {
    fun fullName() = "$first $last"
}

@Serializable
data class LocationDto(
    val street: StreetDto,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String
) {
    fun fullAddress() = "${street.name} ${street.number}, $postcode $city, $country"
}

@Serializable
data class StreetDto(
    val number: Int,
    val name: String
)

@Serializable
data class PictureDto(
    val medium: String
)