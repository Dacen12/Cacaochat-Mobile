package com.project.cacaochat.socketConnection

import io.socket.client.IO
import io.socket.client.Socket

class SocketConnection {
    private var socket : Socket = IO.socket("https://cacaochat.herokuapp.com/")

   fun getSocket(): Socket {
       return socket
   }


}