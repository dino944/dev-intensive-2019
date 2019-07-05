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

}