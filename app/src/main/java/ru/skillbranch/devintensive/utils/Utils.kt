package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?): Pair<String?, String?> {
        //TODO()// FIX ME!!!!
       val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
//        return Pair(firstName, lastName)
        return firstName to lastName
        /*
//        return if (firstName==null) && (lastName==null){(null, null)}
//          return
//        var any = when {
//            (firstName == null) && (lastName == null) -> return null to null
//            (firstName !== null) && (lastName == null) -> return firstName to null!!
 //           (firstName == null) && (lastName != null) -> return null to lastName
//            else -> Pair(firstName, lastName)}
//           else ->
 //           else -> return firstName to lastName
*/
//        return firstName to lastName
    }

    fun transliteration(payload: String, divider:String = " "): String {
        return "PWW"

    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        return "PA"

    }
}