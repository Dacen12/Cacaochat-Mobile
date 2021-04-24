package com.simply.chat

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.simply.chat.socketConnection.SocketConnection
import kotlinx.coroutines.FlowPreview


class Welcome : AppCompatActivity() {

    @FlowPreview
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    window.navigationBarColor = getColor(R.color.white)






    var button = findViewById<Button>(R.id.button_join)

        button.setOnClickListener {
        emit()
        }
    }





fun emit(){
    var username = findViewById<EditText>(R.id.username)
    var room = findViewById<EditText>(R.id.room)


    var intent = Intent(this, Chat::class.java)
    intent.putExtra("username", username.text.toString())
    intent.putExtra("room", room.text.toString())
    startActivity(intent)
}

}
