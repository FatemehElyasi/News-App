package ir.fatemelyasi.news.view.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun String?.toFormattedDate(): String {
    return try {
        if (this.isNullOrBlank()) return ""
        val zonedDateTime = ZonedDateTime.parse(this)
        zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    } catch (e: Exception) {
        ""
    }
}
