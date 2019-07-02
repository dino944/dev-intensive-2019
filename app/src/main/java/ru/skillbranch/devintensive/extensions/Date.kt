package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by Dino944 on 30.06.2019
 */

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy") : String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}
fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND) : Date{
    var time = this.time

    time +=when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY

    }
    this.time = time
    return this

}

fun Date.humanizeDiff(date:Date = Date()): String {
/*
        val T1: String =
        when (Date()) {

                0s - 1с -> "только что"
                1с - 45с ->"несколько секунд назад"
                45с - 75с ->"минуту назад"
                75с - 45мин ->"N минут назад"
                45мин - 75мин ->"час назад"7
                5мин 22ч ->"N часов назад"
                22ч - 26ч ->"день назад"
                26ч - 360д ->"N дней назад"
                >360д ->"более года назад"

            else -> "вчера"
        }

*/
//    return Date()
return "Несколько минут назад"
}


//fun Date.humanizeDiff(date:Date = Date()): String {
 /*   val T1: String =

    return      when (date.add(value = , TimeUnits.MINUTE)) {

                in 0..1   -> "только что"
                1с - 45с ->"несколько секунд назад"
                45с - 75с ->"минуту назад"
                75с - 45мин ->"N минут назад"
                45мин - 75мин ->"час назад"7
                5мин 22ч ->"N часов назад"
                22ч - 26ч ->"день назад"
                26ч - 360д ->"N дней назад"
                    >360д ->"более года назад"

                else -> "вчера"
            }


//    return T1
//return "Вчера"
}
*/
enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY

}