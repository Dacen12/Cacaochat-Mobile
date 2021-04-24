package com.project.cacaochat

import okhttp3.RequestBody

data class Json(
    val username: RequestBody,
    val room : RequestBody
)
