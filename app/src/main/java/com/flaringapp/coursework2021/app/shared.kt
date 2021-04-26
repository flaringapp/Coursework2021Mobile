package com.flaringapp.coursework2021.app

import com.flaringapp.coursework2021.data.text.TextProvider
import org.koin.core.context.GlobalContext

val sharedTextProvider: TextProvider by GlobalContext.get().inject()