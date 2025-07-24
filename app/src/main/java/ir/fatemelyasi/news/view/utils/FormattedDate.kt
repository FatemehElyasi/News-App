package ir.fatemelyasi.news.view.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun String?.toFormattedDate(): String {
    return try {
        if (this.isNullOrBlank()) return ""
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val date = inputFormat.parse(this)
        outputFormat.format(date ?: return "")
    } catch (e: Exception) {
        ""
    }
}
