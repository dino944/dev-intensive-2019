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

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}


/**
 * Extension function to format data into human-friendly type
 */
fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = (date.time - this.time)

    return when (Math.abs(diff / DAY).toInt()) {

        0 -> {
            when (Math.abs(diff / HOUR).toInt()) {
                0 -> {
                    when (Math.abs(diff / MINUTE).toInt()) {
                        0 -> {
                            when (Math.abs(diff / SECOND).toInt()) {
                               in 0..15 -> if ((diff / SECOND).toInt() >= 0) "только что" else "скоро"
      //                          in 0..15 -> if ((diff / SECOND).toInt() >= 0) "несколько секунд назад" else "только что"
                                else -> if ((diff / SECOND).toInt() > 0) "менее минуты назад" else "более чем через минуту"
                            }
                        }
                        1 -> "минуту назад"
                        in 2..4, in 22..24,
                        in 32..34, in 42..44,
                        in 52..54 -> if ((diff / MINUTE).toInt() > 0) "${diff / MINUTE} минуты назад" else "через ${-diff / MINUTE} минуты"
                        21, 31, 41, 51 -> if ((diff / MINUTE).toInt() > 0) "${diff / MINUTE} минуту назад" else "через ${-diff / MINUTE} минуту"
                        else -> if ((diff / MINUTE).toInt() > 0) "${diff / MINUTE} минут назад" else "через ${-diff / MINUTE} минут"
                    }
                }
                1, 21 -> if ((diff / HOUR).toInt() > 0) "${diff / HOUR} час назад" else "через ${diff / HOUR} час"
                in 2..4, in 22..24 -> if ((diff / HOUR).toInt() > 0) "${diff / HOUR} часа назад" else "через ${diff / HOUR} часа"
                else -> if ((diff / HOUR).toInt() > 0) "${diff / HOUR} часов назад" else "через ${diff / HOUR} часов"
            }
        }

        1 -> if ((diff / DAY).toInt() > 0) "вчера" else "завтра"
        in 2..4 -> if ((diff / DAY).toInt() > 0) "${diff / DAY} дня назад" else "через ${-diff / DAY} дня"
        in 5..7 -> if ((diff / DAY).toInt() > 0) "${diff / DAY} дней назад" else "через ${-diff / DAY} дней"
        in 8..14 -> if((diff / DAY).toInt() > 0) "более недели назад" else "более чем через неделю"
        in 15..31 -> if((diff / DAY).toInt() > 0) "более двух недель назад" else "более чем через две недели"
        in 32..61 -> if((diff / DAY).toInt() > 0) "более месяца назад" else "более чем через месяц"
        in 62..182 -> if((diff / DAY).toInt() > 0) "более ${diff / DAY} месяцев назад" else "более чем через ${-diff / DAY} месяца"
        in 183..365 -> if((diff / DAY).toInt() > 0) "более полугода назад" else "более чем через полгода"
        in 365..Int.MAX_VALUE -> if((diff / DAY).toInt() > 0) "более года назад" else "более чем через год"

        else -> "никогда"
    }
}


fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}


fun TimeUnits.plural(value: Int): String {
    return when (this) {
        TimeUnits.SECOND -> "$value секунды"
        TimeUnits.MINUTE -> "$value минуты"
        TimeUnits.HOUR -> "$value часы"
        TimeUnits.DAY -> "$value дни"
    }
}


fun String.truncate(count: Int = 16): String {
    if (count > this.trim().length)
        return this.trim()
    return this.substring(0, count).trim() + "..."
}


fun String.stripHtml(): String {

    var str = this

    while (str.indexOf("<") != -1 && str.indexOf(">") != -1) {
        val toBeReplaced = str.substring(str.indexOf("<"), str.indexOf(">") + 1)
        str = str.replace(toBeReplaced, "")
    }

    str = str.replace("\\s+".toRegex()," ")

    return str.trim()
}


enum class TimeUnits { SECOND, MINUTE, HOUR, DAY }
