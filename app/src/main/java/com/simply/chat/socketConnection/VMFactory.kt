package com.simply.chat.socketConnection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simply.chat.ChatViewModel

class VMFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatViewModel() as T
    }
}