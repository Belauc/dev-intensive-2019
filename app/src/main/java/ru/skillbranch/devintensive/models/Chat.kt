package ru.skillbranch.devintensive.models

class Chat(
    val id: Int,
    val members: MutableList<User> = mutableListOf(),
    val message: MutableList<BaseMessage> = mutableListOf()
) {
}