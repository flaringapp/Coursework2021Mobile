package com.flaringapp.coursework2021.data.network.modifiers.modifier

import okhttp3.Request

interface RequestModifier {

    fun applyChanges(builder: Request.Builder)

}