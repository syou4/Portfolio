package client
/*
import client.Connecter
import client.Inputer
import client.View
*/
import java.net.*


class Main{
    fun main(args:Array<String>) {
        /*
        val connecter : Connecter = Connecter();
        val inputer : Inputer = Inputer();
        val view : View = View();
        */
        val host = "localhost"
        val port = 12345
        val sendData = "hoge".toByteArray()

        send(host, port, sendData)

    }
}

fun send(host: String, port: Int, data: ByteArray, senderPort: Int = 0): Boolean {
    var ret = false
    var socket: DatagramSocket? = null
    try {
        socket = DatagramSocket(senderPort)
        val address = InetAddress.getByName(host)
        val packet = DatagramPacket(data, data.size, address, port)
        socket.send(packet)
        ret = true
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        socket?.close()
    }
    return ret
}
