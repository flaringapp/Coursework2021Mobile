package com.flaringapp.coursework2021.data.text

import android.content.Context
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.app.common.runtimeLocale
import com.flaringapp.coursework2021.data.repository.entity.models.GeoLocation
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TextProviderImpl(
    private val context: Context
): TextProvider {

    companion object {
        private const val DATE_FORMAT = "dd.MM.yyyy"
        private const val DATE_TIME_FORMAT = "HH:mm dd.MM.yyyy"
    }

    private val priceFormat = DecimalFormat("0.00")

    override fun getText(res: Int): CharSequence {
        return context.getString(res)
    }

    override fun getText(res: Int, vararg params: Any): CharSequence {
        return context.getString(res, *params)
    }

    override fun getPluralText(res: Int, count: Int): String {
        return context.resources.getQuantityString(res, count, count)
    }

    override fun formatLocation(location: GeoLocation): CharSequence {
        return "${location.latitude}N, ${location.latitude}W"
    }

    override fun formatArea(area: Float): CharSequence {
        return "$area m2"
    }

    override fun formatNameSurname(name: String, surname: String): CharSequence {
        return "$name $surname"
    }

    override fun formatPrice(price: Int): CharSequence {
        return priceFormat.format(price / 100f)
    }

    override fun formatPriceWithPrefix(price: Int): CharSequence {
        return "${getString(R.string.currency)} ${formatPrice(price)}"
    }

    override fun emptyPriceText(): CharSequence {
        return getText(R.string.no_price)
    }

    override fun noName(): CharSequence {
        return getText(R.string.no_name)
    }

    override fun noRoomName(): CharSequence {
        return getText(R.string.no_room_name)
    }

    override fun formatDate(date: LocalDate): CharSequence {
        return DATE_FORMAT.asFormat().format(date)
    }

    override fun formatDateTime(dateTime: LocalDateTime): CharSequence {
        return DATE_TIME_FORMAT.asFormat().format(dateTime)
    }

    private fun String.asFormat() = SimpleDateFormat(this, runtimeLocale(context))

    private fun String.asDateTimeFormatter() =
        DateTimeFormatter.ofPattern(this).withLocale(runtimeLocale(context))

}