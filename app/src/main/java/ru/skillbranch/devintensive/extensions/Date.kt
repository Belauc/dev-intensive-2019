package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.time.seconds

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}


fun Date.add(value: Int, units: TimeUnits= TimeUnits.SECOND): Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun Date.humanizeDiff(date:Date = Date()): String{

    val difference = date.time - this.time

    return when (TimeUnit.MILLISECONDS.toSeconds(difference)) {
            in 0..1 -> "только что"
            in 1..45 -> "несколько секунд назад"
            in 45..75 -> "минуту назад"
            else -> return when(val time = TimeUnit.MILLISECONDS.toMinutes(difference)){
                in 2..4 -> "$time минуты назад"
                in 4..60 -> "$time минут назад"
                in 61..75 -> "час назад"
                else -> return when(val time = TimeUnit.MILLISECONDS.toHours(difference)){
                    in 1..4 -> "$time часа назад"
                    in 5..20 -> "$time часов назад"
                    in 21..26 -> "день назад"
                    in 26..360 -> "$time дней назад"
                    else -> return "более года назад"

                }
            }
        }


}