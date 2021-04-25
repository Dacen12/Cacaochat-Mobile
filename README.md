<h1>CacaoChat Mobile</h1>

<img src="https://github.com/Dacen12/Cacaochat-Mobile/blob/master/cacaochat_logo.png" width="125" style="position: absolute;">

A simple cross platform chat application [Mobile]



If you would like to test this application in your local environment go to the SocketConnection package 

(Here's a link : https://github.com/Dacen12/Cacaochat-Mobile/blob/master/app/src/main/java/com/project/cacaochat/socketConnection/SocketConnection.kt) 

And change this piece of code to your respective localhost url:

`private var socket : Socket = IO.socket("https://cacaochat.herokuapp.com/")` 

And add port 3000. For example:

`private var socket : Socket = IO.socket("http://localhost:3000/")` 

The port on the server side is by default set to 3000. (Although this can be changed)                


Here's a video gif of the application in action: https://imgur.com/a/oJmUvpi 
