package ru.skillbranch.devintensive.extensions

import java.lang.IllegalArgumentException
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val  HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"): String {
    var dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}
fun Date.add(value:Int, timeUnit: TimeUnits): Date{
    var time = this.time

    time += when(timeUnit){
        TimeUnits.SECOND -> SECOND * value
        TimeUnits.MINUTE ->  MINUTE * value
        TimeUnits.HOUR ->  HOUR * value
        TimeUnits.DAY ->  DAY * value
    }
    this.time = time
    return this
}
fun Date.humanizeDiff(date: Date = Date()): String{
    fun getMinute(mill: Long) : String{
        val min =  abs(mill/1000L/60)
        if(min in 10..20){
            return "$min минут"
        }
        val millStrLast = min.toString().last()
        return when(millStrLast){
            '1' -> "$min минуту"
            '2','3','4' -> "$min минуты"
            else-> "$min минут"
        }
    }
    fun getHour(mill: Long) : String{
        val hour =  abs(mill/1000L/60/60)
        if(hour in 10..20){
            return "$hour часов"
        }
        val millStrLast = hour.toString().last()
        return when(millStrLast){
            '1' -> "$hour час"
            '2','3','4' -> "$hour часа"
            else-> "$hour часов"
        }
    }
    fun getDay(mill: Long) : String{
        val day =  abs(mill/1000L/60/60/24)
        if(day in 10..320){
            return "$day дней"
        }
        val millStrLast = day.toString().last()
        return when(millStrLast){
            '1' -> "$day день"
            '2','3','4' -> "$day дня"
            else-> "$day дней"
        }
    }
    val diff = this.time - date.time;
    return when(diff){
        in -1 * SECOND..1 * SECOND-> "только что"
        in -45 * SECOND..-1* SECOND-> "несколько секунд назад"
        in 1* SECOND..45 * SECOND-> "через несколько секунд"
        in -75 * SECOND..-45 * SECOND -> "минуту назад"
        in 45 * SECOND..75 * SECOND -> "через минуту"
        in -45 * MINUTE ..-75 * SECOND-> "${getMinute(diff)} назад"
        in 75 * SECOND..45 * MINUTE -> "через ${getMinute(diff)}"
        in -75 * MINUTE ..-45 * MINUTE-> "час назад"
        in 45 * MINUTE..75 * MINUTE -> "через час"
        in -22 * HOUR  ..-75 * MINUTE-> "${getHour(diff)} назад"
        in 75 * MINUTE..22 * HOUR -> "через ${getHour(diff)}"
        in -26 * HOUR  ..-22 * HOUR-> "день назад"
        in 22 * HOUR..26* HOUR -> "через день"
        in -360 * DAY  ..-26 * HOUR-> "${getDay(diff)} назад"
        in 26 * HOUR..360* DAY -> "через ${getDay(diff)}"
        in Long.MIN_VALUE..-360 * DAY -> "более года назад"
        in 360 * DAY.. Long.MAX_VALUE-> "более чем через год"
        else -> throw IllegalArgumentException("$diff")
    }
}


enum class TimeUnits{
    SECOND{
        override fun plural(value: Int) :String{

            return makeString(value,Triple("секунд", "секунду", "секунды"))

        }
    }, MINUTE {
        override fun plural(value: Int) :String{
            return makeString(value,Triple("минут", "минуту", "минуты" ))
        }
    }, HOUR {
        override fun plural(value: Int):String {
            return makeString(value,Triple("часов", "час", "часа"))

        }
    }, DAY {
        override fun plural(value: Int) :String{
            return makeString(value,Triple("дней", "день", "дня"))
        }
    };
    abstract fun plural(value: Int): String
    companion object Utils{
        private fun makeString(value: Int, triple: Triple<String, String, String>) : String {
            if (value in 10..20) {
                return "$value ${triple.first}"
            }
            return when (value.toString().last()) {
                '1' -> "$value ${triple.second}"
                '2', '3', '4' -> "$value ${triple.third}"
                else -> "$value ${triple.first}"
            }
        }
    }
}
