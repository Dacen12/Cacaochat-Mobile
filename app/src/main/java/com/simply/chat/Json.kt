package com.simply.chat

import okhttp3.RequestBody

data class Json(
    val username: RequestBody,
    val room : RequestBody
)
