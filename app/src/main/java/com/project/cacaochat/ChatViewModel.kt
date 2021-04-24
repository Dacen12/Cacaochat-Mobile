package com.project.cacaochat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatViewModel() : ViewModel() {
private var _listOfMessages = MutableLiveData<MutableList<Message>>()
        val listOfMessages : LiveData<MutableList<Message>>
    get() = _listOfMessages
    var messages= mutableListOf<Message>()


    fun addMessagesToRecyclerView(data: Message){

        messages.add(data)
        Log.d("Data","Messages size: ${messages.size}")
        Log.d("Data", "VM LIST: ${messages}")
        _listOfMessages.value = messages
        Log.d("Data", " listMesasges value : ${listOfMessages.value}")
    }


}