package com.flaringapp.coursework2021.data.text

import android.content.Context
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.data.repository.entity.models.GeoLocation
import java.text.DecimalFormat

class TextProviderImpl(
    private val context: Context
): TextProvider {

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
}