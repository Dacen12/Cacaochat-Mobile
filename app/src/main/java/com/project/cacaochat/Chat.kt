package com.project.cacaochat

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Klaxon

import com.project.cacaochat.databinding.ActivityChatBinding
import com.project.cacaochat.socketConnection.SocketConnection
import com.project.cacaochat.socketConnection.VMFactory
import io.socket.emitter.Emitter
import org.json.JSONException
import org.json.JSONObject


class Chat : AppCompatActivity() {
    private lateinit var binding : ActivityChatBinding
    private lateinit var viewmodel: ChatViewModel
    private lateinit var vmFactory : VMFactory
    private  var socket = SocketConnection().getSocket()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        socket.connect()
        vmFactory = VMFactory()
        viewmodel = ViewModelProvider(this, vmFactory).get(ChatViewModel::class.java)
        var adapter = RecyclerAdapter()

        var getUsername = intent.extras!!.get("username")

        var getRoom = intent.extras!!.get("room")

        binding.sendMessage.setOnClickListener {
            addMessagesToRecyclerView(getUsername.toString())
            sendMessage(getUsername.toString())
            binding.inputMessage.setText("")
        }



        initAdapter(adapter)
        loadMessagesIntoRecyclerView(adapter)
        connect(getUsername.toString(), getRoom.toString())
        getMessagesFromOtherUser(this, adapter)

    }


    fun connect( username: String, room: String){
    socket.emit("MobileRoomJoined", username, room )
    }

    fun getMessagesFromOtherUser(activity: Chat, adapter: RecyclerAdapter){


        socket.on("receivedMessage", object: Emitter.Listener{
            override fun call(vararg args: Any?) {
              activity.runOnUiThread(kotlinx.coroutines.Runnable {
                  kotlin.run {

                     var obj = JSONObject.wrap(args[0])
                    val result = Klaxon().parse<Message>(obj.toString())
                      Log.d("Data", "${obj}")
                      try{
                          result?.let {
                              viewmodel.addMessagesToRecyclerView(result)
                          }
                      } catch (e: JSONException){
                          Log.d("Data", e.message!!)
                      }
                  }
              })
            }
        })
    }

    fun initAdapter(adapter: RecyclerAdapter){
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.recyclerView.adapter = adapter

    }

   fun loadMessagesIntoRecyclerView(adapter: RecyclerAdapter){
       viewmodel.listOfMessages.observe(this){
           adapter.dataChanged(it)
           Log.d("Data", " SIZE: ${it.size}")
       }
   }

    fun addMessagesToRecyclerView(username: String){
        val inputFromThisUser = binding.inputMessage.text
        var message = Message(username, inputFromThisUser.toString())
        viewmodel.addMessagesToRecyclerView(message)
    }

    fun sendMessage(username: String){
     socket.emit("messageToOthers", username, binding.inputMessage.text.toString())
    }
}



data class Message(
    val username: String,
    val message: String
)

// RecyclerView

class RecyclerAdapter() : RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>(){
    var messageList = mutableListOf<Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.message_layout, parent, false)
        return CustomViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
       holder.itemView.findViewById<TextView>(R.id.messageBox).text = "${messageList[position].username}  :   ${messageList[position].message}"
    }

    override fun getItemCount(): Int {
        Log.d("Data", "MEssage list : ${messageList.size}")
        return this.messageList.size
    }



    fun dataChanged(messages: MutableList<Message>){
        this.messageList = messages
        notifyDataSetChanged()
    }

    class CustomViewHolder(views: View) : RecyclerView.ViewHolder(views)
}