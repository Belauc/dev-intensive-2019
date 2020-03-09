package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(val id:String,
           var firstName : String?,
           var lastName : String?,
           var avatar : String?,
           var rating : Int = 0,
           var respect : Int = 0,
           var lastVisit : Date? = Date(),
           var isOnline : Boolean = false
){
    constructor(id: String, firstName: String?, lastName: String?) :this(
        id,
        firstName,
        lastName,
        avatar = null
    )

    constructor(id: String): this(id, "John", "Doe")

    init {
        println("Создан новый обьект с именем $lastName")
    }

    companion object Factory {
        private var lastId = -1
        fun makeUser(fullName: String): User {
            lastId ++

            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User(id = "lastId", firstName = firstName, lastName = lastName)
        }
    }


}