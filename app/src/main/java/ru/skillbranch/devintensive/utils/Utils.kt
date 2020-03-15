package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?>{

        return if (fullName.isNullOrBlank()) Pair(null,null)
        else{
            val parts: List<String>? = fullName.split(" ")

            val firstName = parts?.getOrNull(0)
            val lastName = parts?.getOrNull(1)

            Pair(firstName, lastName)
        }
    }


    fun toInitials(firstName:String?, lastName:String?): String? {

        return if (firstName.isNullOrBlank() && lastName.isNullOrBlank()) null
        else{
            var initials = if (!firstName.isNullOrBlank()) lastName?.getOrNull(0).toString() else ""
            initials += if (!lastName.isNullOrBlank()) lastName.getOrNull(0).toString() else ""
            initials.toUpperCase()
        }
    }


    fun transliteration(str: String, divider:String = "_"): String {

        val repl = str.replace(Regex("[А-я]")){
            when (it.value)
            {
            "а"-> "a"
            "б"-> "b"
            "в"-> "v"
            "г"-> "g"
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
            "A"-> "A"
            "Б"-> "B"
            "В"-> "V"
            "Г"-> "G"
            "Д"-> "D"
            "Е"-> "E"
            "Ё"-> "E"
            "Ж"-> "ZH"
            "З"-> "Z"
            "И"-> "I"
            "Й"-> "I"
            "К"-> "k"
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
                else -> ""
            }
        }

        var result = repl.split(" ")[0]
        if (repl.split(" ").size > 1) {
            result += divider
            result += repl.split(" ")[1]
        }
        return result
    }
}