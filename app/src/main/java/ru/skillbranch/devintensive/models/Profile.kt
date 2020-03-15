package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils

data class Profile(
    val firstName: String,
    val lastName: String,
    val city: String
){
    val nickName: String = Utils.transliteration("$firstName $lastName")

    fun toMap(): Map<String, Any> = mapOf(
        "firstName" to firstName,
        "lastName" to lastName,
        "nickName" to nickName,
        "city" to city
    )
}
