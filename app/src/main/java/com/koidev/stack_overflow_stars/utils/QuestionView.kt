package com.koidev.stack_overflow_stars.utils

import android.content.res.Resources
import com.koidev.stack_overflow_stars.R
import org.threeten.bp.Duration
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.HttpException
import java.io.IOException

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

//@DrawableRes
//fun AnswerIcon.getIcon() = when (this) {
//    AnswerIcon.OPEN -> R.drawable.ic_event_created_24dp
//    AnswerIcon.CLOSE -> R.drawable.ic_event_imported_24dp
//}
