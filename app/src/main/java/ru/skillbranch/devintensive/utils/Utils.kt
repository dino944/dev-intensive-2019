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

    fun transliteration(payload: String?, divider:String = " "): String {
//val TT:String = ""
      val tT : String
        if (payload == null) return "нет" else
        tT  = payload.replace(Regex("[абвгдеёжзийклмнопрстуфхцшщъыьэюя АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦШЩЪЫЬЭЮЯ]")) {
                    when(it.value) {
                        " " -> "_"
                        "а"-> "a"
                        "б"-> "b"
                        "в"-> "v"
                        "г"-> "g"
                        " "-> "_"
                        "д"-> "d"
                        "е"-> "e"
                        "ё"-> "e"
                        "ж"-> "zh"
                        "з"-> "z"
                        "и"-> "i"
                        "й"-> "i"
                        "к"-> "k"
                        "л"-> "l"
                        "м"-> "m"
                        "н"-> "n"
                        "о"-> "o"
                        "п"-> "p"
                        "р"-> "r"
                        "с"-> "s"
                        "т"-> "t"
                        "у"-> "u"
                        "ф"-> "f"
                        "х"-> "h"
                        "ц"-> "c"
                        "ч"-> "ch"
                        "ш"-> "sh"
                        "щ"-> "sh'"
                        "ъ"-> ""
                        "ы"-> "i"
                        "ь"-> ""
                        "э"-> "e"
                        "ю"-> "yu"
                        "я"-> "ya"

                        //
                        "А"-> "A"
                        "Б"-> "B"
                        "В"-> "V"
                        "Г"-> "G"
                       //
                        "Д"-> "D"
                        "Е"-> "E"
                        "Ё"-> "E"
                        "Ж"-> "ZH"
                        "З"-> "Z"
                        "И"-> "I"
                        "Й"-> "I"
                        "К"-> "K"
                        "Л"-> "L"
                        "М"-> "M"
                        "Н"-> "N"
                        "О"-> "O"
                        "П"-> "P"
                        "Р"-> "R"
                        "С"-> "S"
                        "Т"-> "T"
                        "У"-> "U"
                        "Ф"-> "F"
                        "Х"-> "H"
                        "Ц"-> "C"
                        "Ч"-> "CH"
                        "Ш"-> "SH"
                        "Щ"-> "SH'"
                        "Ъ"-> ""
                        "Ы"-> "I"
                        "Ь"-> ""
                        "Э"-> "E"
                        "Ю"-> "YU"
                        "Я"-> "YA"




                        else -> it.value

                    }
                }


        return tT

    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val Let1: String? = firstName?.get(0).toString()
        val Let2: String? = lastName?.get(0).toString()
        return transliteration(Let1 + Let2)

    }
}