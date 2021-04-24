package com.simply.chat.socketConnection

import io.socket.client.IO
import io.socket.client.Socket

class SocketConnection {
    private var socket : Socket = IO.socket("http://192.168.1.175:3000/")

   fun getSocket(): Socket {
       return socket
   }


}