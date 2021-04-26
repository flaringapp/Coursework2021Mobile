package com.flaringapp.coursework2021.data.network.modifiers.modifier

import com.flaringapp.coursework2021.app.common.takeIfNotEmpty
import com.flaringapp.coursework2021.data.network.NetworkConstants
import com.flaringapp.coursework2021.data.storage.DataStorage
import okhttp3.Request
import org.koin.core.context.GlobalContext

class RequestTokenAppender: RequestModifier {

    private val dataStorage: DataStorage by GlobalContext.get().inject()

    override fun applyChanges(builder: Request.Builder) {
        val token = dataStorage.token.takeIfNotEmpty() ?: return
        builder.header(NetworkConstants.API_KEY, token)
    }
}