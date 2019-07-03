package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?): Pair<String?, String?> {

       val parts: List<String>? = fullName?.split(" ")
        var firstName:String? = null
        var lastName:String? = null
        if (parts?.getOrNull(0) != "")
            firstName = parts?.getOrNull(0)
        if (parts?.getOrNull(1) != "")
            lastName = parts?.getOrNull(1)
        return Pair(firstName, lastName)

    }

    fun transliteration(payload: String, divider: String = " "): String {
//val TT:String = ""
      var tT = ""
        payload.forEach {  
        tT += when(it) {
                        ' ' -> " "
                        'а'-> "a"
                        'б'-> "b"
                        'в'-> "v"
                        'г'-> "g"
                        'д'-> "d"
                        'е'-> "e"
                        'ё'-> "e"
                        'ж'-> "zh"
                        'з'-> "z"
                        'и'-> "i"
                        'й'-> "i"
                        'к'-> "k"
                        'л'-> "l"
                        'м'-> "m"
                        'н'-> "n"
                        'о'-> "o"
                        'п'-> "p"
                        'р'-> "r"
                        'с'-> "s"
                        'т'-> "t"
                        'у'-> "u"
                        'ф'-> "f"
                        'х'-> "h"
                        'ц'-> "c"
                        'ч'-> "ch"
                        'ш'-> "sh"
                        'щ'-> "sh'"
                        'ъ'-> ""
                        'ы'-> "i"
                        'ь'-> ""
                        'э'-> "e"
                        'ю'-> "yu"
                        'я'-> "ya"

                        //
                        'А'-> "A"
                        'Б'-> "B"
                        'В'-> "V"
                        'Г'-> "G"
                       //
                        'Д'-> "D"
                        'Е'-> "E"
                        'Ё'-> "E"
                        'Ж'-> "ZH"
                        'З'-> "Z"
                        'И'-> "I"
                        'Й'-> "I"
                        'К'-> "K"
                        'Л'-> "L"
                        'М'-> "M"
                        'Н'-> "N"
                        'О'-> "O"
                        'П'-> "P"
                        'Р'-> "R"
                        'С'-> "S"
                        'Т'-> "T"
                        'У'-> "U"
                        'Ф'-> "F"
                        'Х'-> "H"
                        'Ц'-> "C"
                        'Ч'-> "CH"
                        'Ш'-> "SH"
                        'Щ'-> "SH'"
                        'Ъ'-> ""
                        'Ы'-> "I"
                        'Ь'-> ""
                        'Э'-> "E"
                        'Ю'-> "YU"
                        'Я'-> "YA"
            
                        else -> it.toString()
                    }
                }
        return tT
    }
/*
    fun toInitials(firstName: String?, lastName: String?): String? {
        var iT: String
        if ((firstName?.replace(" ", "") == "" && lastName?.replace(" ", "") == "") ||
                (firstName == null && lastName == null))
            return null
        else
            return ("${firstName?.replace(" ", "")?.getOrNull(0) ?: ""}" +
                    " ${lastName?.replace(" ", "")?.getOrNull(0)
                    ?: ""}").toUpperCase()


    }
    */
fun toInitials(firstName: String?, lastName: String?): String {
    val Let1: String? = firstName?.get(0).toString()
    val Let2: String? = lastName?.get(0).toString()
    return transliteration(Let1 + Let2)

}

}