package com.example.practice_tvshowapp.utils

import android.annotation.SuppressLint
import android.content.Context
import com.example.practice_tvshowapp.models.tvshows.Schedule
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

object CommonUtils {
    private const val DATE_FORMAT = "yyyy-MM-dd"
    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat(DATE_FORMAT)

    fun getYesterdayDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val timeToDate = calendar.time

        return formatter.format(timeToDate)
    }

    /**
     *  convertDpToPx : dp -> px
     *  @param dp
     *  @param context
     *
     *  @return px : Int
     */
    fun convertDpToPx(dp : Int, context: Context): Int {
        val density: Float =  context.resources.displayMetrics.density
        return (dp.toFloat() * density).roundToInt()
    }


    /**
     * getTvShowInfoDate
     *
     * { "premiered": "2013-06-24", "ended": "2015-09-10" }
     * -> 2023-06-24 ~ 2015-09-10
     */
    fun getTvShowInfoDate(premiered: String?, ended: String?): String = "${premiered ?: ""} ~ ${ended ?: ""}"

    /**
     *  getTvShowInfoTime
     *
     *  "schedule":{ "time": "22:00", "days": ["Tuesday"] }
     * -> Tue, 22:00
     */
    fun getTvShowInfoTime(schedule: Schedule): String {
        val days = schedule.days.joinToString(separator = ",") { day ->
            day.take(3)
        }

        return "$days ${schedule.time}"
    }


    /**
     *  getTvShowInfoGenres
     *
     *  "genres": [ "Drama", "Science-Fiction", "Thriller" ]
     *  -> Drama  |  Science-Fiction  |  Thriller
     */
    fun getTvShowInfoGenres(genres: List<String>): String = genres.joinToString(separator = "  |  ")
}