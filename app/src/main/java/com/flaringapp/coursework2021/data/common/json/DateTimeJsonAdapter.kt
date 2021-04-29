package com.flaringapp.coursework2021.data.common.json

import com.squareup.moshi.*
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class DateTimeJsonAdapter : JsonAdapter<LocalDateTime>() {

    companion object {
        private val FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            .withZone(ZoneOffset.UTC)
    }

    @FromJson
    @Synchronized
    override fun fromJson(reader: JsonReader): LocalDateTime? {
        return try {
            val dateAsString = reader.nextString()
            LocalDateTime.parse(dateAsString, FORMATTER)
        } catch (e: Exception) {
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: LocalDateTime?) {
        if (value != null) {
            FORMATTER.format(value)
        }
    }
}