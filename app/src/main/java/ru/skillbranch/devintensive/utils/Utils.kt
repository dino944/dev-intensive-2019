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
    fun toInitials(firstName: String?, lastName: String?): String? {
        var initials = ""

        if (!firstName.isNullOrBlank()) {
            initials += "${firstName.capitalize().get(0)}"
        }
        if (!lastName.isNullOrBlank()) {
            initials += "${lastName.capitalize().get(0)}"
        }
        if (firstName.isNullOrBlank() && lastName.isNullOrBlank())
            return null

        return initials
    }
/*
    fun transliteration(fullName: String?, divider: String = " "): String {

        val literas = mutableMapOf(
                "а" to "a",
                "б" to "b",
                "в" to "v",
                "г" to "g",
                "д" to "d",
                "е" to "e",
                "ё" to "e",
                "ж" to "zh",
                "з" to "z",
                "и" to "i",
                "й" to "i",
                "к" to "k",
                "л" to "l",
                "м" to "m",
                "н" to "n",
                "о" to "o",
                "п" to "p",
                "р" to "r",
                "с" to "s",
                "т" to "t",
                "у" to "u",
                "ф" to "f",
                "х" to "h",
                "ц" to "c",
                "ч" to "ch",
                "ш" to "sh",
                "щ" to "sh'",
                "ъ" to "",
                "ы" to "i",
                "ь" to "",
                "э" to "e",
                "ю" to "yu",
                "я" to "ya"
        )

        val (firstName, lastName) = parseFullName(fullName)

        var transFirstName = ""
        var transLastName = ""

        if (!firstName.isNullOrBlank()) {

            firstName.decapitalize()

            for (it in firstName.toCharArray()) {
                if (literas.containsKey(it.toLowerCase().toString())) {
                    transFirstName += literas.get(it.toLowerCase().toString())
                }
                else {
                    transFirstName += it.toLowerCase().toString()
                }
            }
        }

        if (!lastName.isNullOrBlank()) {

            lastName.decapitalize()

            for (it in lastName.toCharArray()) {
                if (literas.containsKey(it.toLowerCase().toString())) {
                    transLastName += literas.get(it.toLowerCase().toString())
                }
                else {
                    transLastName += it.toLowerCase().toString()
                }
            }
        }

        transFirstName = transFirstName.capitalize()
        transLastName = transLastName.capitalize()

        if (transFirstName.isBlank() || transLastName.isBlank())
            return "$transFirstName$transLastName"

        return "$transFirstName$divider$transLastName"
    }
*/

    fun transliteration(payload: String, divider: String = " "): String {
        val mapping = payload.map {
            when (it) {
                ' ' -> divider
                'а' -> 'a'
                'б' -> 'b'
                'в' -> 'v'
                'г' -> 'g'
                'д' -> 'd'
                'е' -> 'e'
                'ё' -> 'e'
                'ж' -> "zh"
                'з' -> 'z'
                'и' -> 'i'
                'й' -> 'i'
                'к' -> 'k'
                'л' -> 'l'
                'м' -> 'm'
                'н' -> 'n'
                'о' -> 'o'
                'п' -> 'p'
                'р' -> 'r'
                'с' -> 's'
                'т' -> 't'
                'у' -> 'u'
                'ф' -> 'f'
                'х' -> 'h'
                'ц' -> 'c'
                'ч' -> "ch"
                'ш' -> "sh"
                'щ' -> "sh'"
                'ъ' -> ""
                'ы' -> 'i'
                'ь' -> ""
                'э' -> 'e'
                'ю' -> "yu"
                'я' -> "ya"
                'А' -> 'A'
                'Б' -> 'B'
                'В' -> 'V'
                'Г' -> 'G'
                'Д' -> 'D'
                'Е' -> 'E'
                'Ё' -> 'E'
                'Ж' -> "Zh"
                'З' -> 'Z'
                'И' -> 'I'
                'Й' -> 'I'
                'К' -> 'K'
                'Л' -> 'L'
                'М' -> 'M'
                'Н' -> 'N'
                'О' -> 'O'
                'П' -> 'P'
                'Р' -> 'R'
                'С' -> 'S'
                'Т' -> 'T'
                'У' -> 'U'
                'Ф' -> 'F'
                'Х' -> 'H'
                'Ц' -> 'C'
                'Ч' -> "Ch"
                'Ш' -> "Sh"
                'Щ' -> "Sh'"
                'Ъ' -> ""
                'Ы' -> 'I'
                'Ь' -> ""
                'Э' -> 'E'
                'Ю' -> "Yu"
                'Я' -> "Ya"
                else -> it
            }
        }

        return mapping.joinToString(separator = "")
    }



}