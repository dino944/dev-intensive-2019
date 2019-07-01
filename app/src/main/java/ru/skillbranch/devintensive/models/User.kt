package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Created by Dino944 jn 28.06.2019.
 **/
data class User (
        val id: String,
        var firstName: String?,
        var lastName: String?,
        var avatar: String?,
        var rating: Int = 0,
        var respect: Int = 0,
        val lastVisit: Date? = Date(),
        val isOnline: Boolean = false
//        var introBit :String// = "$firstName $lastName !!!!!!"
) {
//    var introBit :String
//    var introBit :String =getIntro()

    constructor(id: String, firstName: String?, lastName: String?) : this(
            id = id,
            firstName = firstName,
            lastName = lastName,
            avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe")

    init {
//        introBit = getIntro()
        println("It's Alive!!!\n" +
                "${if (lastName === "Doe") "His name id $firstName $lastName" else "And his name is $firstName $lastName!!!!"}\n")
        //       "${getIntro()}")
    }

    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId++




            val (firstName, lastName) = Utils.parseFullName(fullName)
//            return User(id= "$lastId", firstName = firstName, lastName = lastName)

            when {
                (firstName == "") && (lastName == "") -> return User(id= "$lastId", firstName = "null", lastName = "null")
                (firstName !== "") && (lastName == "") -> return User(id = "$lastId", firstName = firstName, lastName = "null")
                (firstName == "") && (lastName != "") -> return User(id= "$lastId", firstName = "null", lastName = lastName)
//            else -> Pair(firstName, lastName)}
//           else ->
                else -> return User(id = "$lastId", firstName = firstName, lastName = lastName)
            }

            /**   private fun getIntro()="""
            df df df dfdfdfdf !!!!
            df df df dfffffff ...

            df df df dfdfdfdf !!!!
            df df df dfffffff ...
            ${"\n"}

            $firstName $lastName
            """.trimIndent()

            fun printMe() : Unit{
            println("""
            id: $id
            firstName: $firstName
            lastName: $lastName
            avatar: $avatar
            rating: $rating
            respect: $respect
            lastVisit: $lastVisit
            isOnline: $isOnline
            """.trimIndent())
            }
             **/
        }
    }
}
