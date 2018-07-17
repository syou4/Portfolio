//package client
/*
import client.Connecter
import client.View
*/
//import Inputer
import java.net.*

fun main(args:Array<String>) {
    /*
    val connecter : Connecter = Connecter();
    val inputer : Inputer = Inputer();
    val view : View = View();
    */

    /*
    val host = "localhost"
    val port = 12345
    val sendData = "hoge".toByteArray()

    send(host, port, sendData)
    println("Sent message")
    */

    //val inputer = Inputer()
    /*
    while(true){
        val inputCommand = readLine()
        if(inputCommand)
    }
    */

    //送信
    send()

    //2秒待機
    Thread.sleep(100)

    //受信待ち
    receive()
}

fun send(){
    //送信用ソケット
    var socket = DatagramSocket()
    //送信データ
    var data = ByteArray(128)
    //送信の仮ヘッダ
    val header = (0 shl 30) or (4 shl 27) or (3 shl 24) or (15 shl 16) or (4 shl 8)
    data[0] = (header shr 24).toByte()
    data[1] = (header shr 16).toByte()
    data[2] = (header shr 8).toByte()
    data[3] = (header shr 0).toByte()

    //指定送信先に対するパケット
    var pkt = DatagramPacket(data, data.size,Inet4Address.getByName("localhost"),12345)

    System.out.println("Sending ...")
    //送信
    socket.send(pkt)
    System.out.println("Sent ...")
}

fun receive(){
    //受信ソケット
    var socket = DatagramSocket(12345)
    //受信用バッファ
    var data = ByteArray(128)
    //受信用パケット
    var pkt = DatagramPacket(data, data.size)
    System.out.println("Receiving...")
    //受信待機
    socket.receive(pkt)
    System.out.println("Received...")

    //受信データ表示(バイト単位)
    System.out.println(data.copyOf(pkt.length).joinToString(
        separator = " ",
        transform = { b -> String.format("%02x", b) }
    ))


}
/*
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
*/