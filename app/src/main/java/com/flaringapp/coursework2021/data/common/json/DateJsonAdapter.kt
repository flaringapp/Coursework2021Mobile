package com.flaringapp.coursework2021.data.common.json

import com.squareup.moshi.*
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class DateJsonAdapter : JsonAdapter<LocalDate>() {

    companion object {
        private val FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            .withZone(ZoneOffset.UTC)
    }

    @FromJson
    @Synchronized
    override fun fromJson(reader: JsonReader): LocalDate? {
        return try {
            val dateAsString = reader.nextString()
            LocalDate.parse(dateAsString, FORMATTER)
        } catch (e: Exception) {
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: LocalDate?) {
        if (value != null) {
            FORMATTER.format(value)
        }
    }
}