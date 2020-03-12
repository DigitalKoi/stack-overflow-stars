package com.koidev.stackoverflowstars.utils

import android.content.res.Resources
import com.koidev.stackoverflowstars.R
import org.threeten.bp.Duration
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.HttpException
import java.io.IOException
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

fun Throwable.userMessage(resources: Resources) = when (this) {
    is HttpException -> when (this.code()) {
        304 -> resources.getString(R.string.not_modified_error)
        400 -> resources.getString(R.string.bad_request_error)
        401 -> resources.getString(R.string.unauthorized_error)
        403 -> resources.getString(R.string.forbidden_error)
        404 -> resources.getString(R.string.not_found_error)
        405 -> resources.getString(R.string.method_not_allowed_error)
        409 -> resources.getString(R.string.conflict_error)
        422 -> resources.getString(R.string.unprocessable_error)
        500 -> resources.getString(R.string.server_error_error)
        else -> resources.getString(R.string.unknown_error)
    }
    is IOException -> resources.getString(R.string.network_error)
    else -> resources.getString(R.string.unknown_error)
}

private val DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy")
fun ZonedDateTime.humanTime(resources: Resources): String {
    val delta = Duration.between(this, ZonedDateTime.now())
        .seconds
        .let { maxOf(0, it) }

    val timeStr = when {
        delta < 60 -> resources.getString(R.string.time_sec, delta)
        delta < 60 * 60 -> resources.getString(R.string.time_min, delta / 60)
        delta < 60 * 60 * 24 -> resources.getString(R.string.time_hour, delta / (60 * 60))
        delta < 60 * 60 * 24 * 7 -> resources.getString(R.string.time_day, delta / (60 * 60 * 24))
        else -> return this.toLocalDate().format(DATE_FORMAT)
    }

    return resources.getString(R.string.time_ago, timeStr)
}

fun Long.humanTime(resources: Resources): String {

    var time = this

    if (time < 1000000000000L) {
        time *= 1000;
    }

    val instant = Instant.ofEpochMilli(time)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    val delta = Duration.between(localDateTime, ZonedDateTime.now())
        .seconds
        .let { maxOf(0, it) }

    val timeStr = when {
        delta < 60 -> resources.getString(R.string.time_sec, delta)
        delta < 60 * 60 -> resources.getString(R.string.time_min, delta / 60)
        delta < 60 * 60 * 24 -> resources.getString(R.string.time_hour, delta / (60 * 60))
        delta < 60 * 60 * 24 * 7 -> resources.getString(R.string.time_day, delta / (60 * 60 * 24))
        else -> return localDateTime.toLocalDate().format(DATE_FORMAT)
    }

    return resources.getString(R.string.time_ago, timeStr)
}

fun Long.prettyCount(): String? {
    val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
    val value = floor(log10(this.toDouble())).toInt()
    val base = value / 3
    return if (value >= 3 && base < suffix.size) {
        DecimalFormat("#0.0").format(
            this / 10.0.pow(base * 3.toDouble())
        ) + suffix[base]
    } else {
        DecimalFormat("#,##0").format(this)
    }
}


