package ir.fatemelyasi.news.view.utils

import java.text.SimpleDateFormat
import java.util.*

fun convertDate(input: String?): String {
    return try {
        if (input.isNullOrBlank()) return ""

        val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        isoFormat.timeZone = TimeZone.getTimeZone("UTC")

        val date = isoFormat.parse(input)
        val outputFormat = SimpleDateFormat("yyyy-dd-M", Locale.getDefault())
        outputFormat.format(date!!)
    } catch (e: Exception) {
        ""
    }
}


fun extractDatePart(input: String): String {
    return try {
        val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        isoFormat.timeZone = TimeZone.getTimeZone("UTC")

        val date = isoFormat.parse(input)

        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        outputFormat.format(date!!)
    } catch (e: Exception) {
        ""
    }
}


