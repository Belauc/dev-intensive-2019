package ru.skillbranch.devintensive.models

class UserView(
    val id: Int,
    val fullName: String,
    val nickName: String,
    val avatars: String? = null,
    val status: String = "offline",
    val initials: String?

) {
}